package hexlet.code.service;

import hexlet.code.dto.UserDtoRq;
import hexlet.code.dto.UserDtoRs;
import hexlet.code.repository.UserRepository;
import hexlet.code.repository.model.User;
import hexlet.code.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDtoRs getUserById(Long id) {
        return userMapper.toUserDto(userRepository.getById(id));
    }

    public List<UserDtoRs> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserDto).toList();
    }

    public UserDtoRs createUser(UserDtoRq user) {
        User newUser = userRepository.save(userMapper.toUser(user));
        return userMapper.toUserDto(newUser);
    }

    public UserDtoRs updateUser(UserDtoRq user, Long id) {
        User oldUser = userRepository.getById(id);
        oldUser.setEmail(user.getEmail());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User updatedUser = userRepository.save(oldUser);
        return userMapper.toUserDto(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
