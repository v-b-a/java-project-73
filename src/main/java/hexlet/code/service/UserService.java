package hexlet.code.service;

import hexlet.code.repository.model.User;
import hexlet.code.repository.UserRepository;
import hexlet.code.dto.UserDtoRq;
import hexlet.code.dto.UserDtoRs;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static hexlet.code.config.SecurityConfig.DEFAULT_AUTHORITIES;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDtoRs getUserById(Long id) {
        return toUserDto(userRepository.getById(id));
    }

    public List<UserDtoRs> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::toUserDto).toList();
    }

    public UserDtoRs createUser(UserDtoRq user) {
        User newUser = userRepository.save(toUser(user));
        return toUserDto(newUser);
    }

    public UserDtoRs updateUser(UserDtoRq user, Long id) {
        User oldUser = userRepository.getById(id);
        oldUser.setEmail(user.getEmail());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User updatedUser = userRepository.save(oldUser);
        return toUserDto(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDtoRs toUserDto(User user) {
        return UserDtoRs.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createAt(user.getCreatedAt())
                .build();
    }

    private User toUser(UserDtoRq user) {
        return User.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(this::buildSpringUser)
                .orElseThrow(() -> new UsernameNotFoundException("Not found user with 'username': " + username));
    }

    private UserDetails buildSpringUser(final User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                DEFAULT_AUTHORITIES
        );
    }
}
