package hexlet.code.service;

import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import hexlet.code.repository.dto.UserDtoRq;
import hexlet.code.repository.dto.UserDtoRs;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
}
