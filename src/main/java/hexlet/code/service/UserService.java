package hexlet.code.service;

import hexlet.code.controller.mapper.UserMapper;
import hexlet.code.dto.requestDto.UserDtoRq;
import hexlet.code.dto.responseDto.UserDtoRs;
import hexlet.code.repository.UserRepository;
import hexlet.code.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDtoRs getUserById(Long id) {
        return userMapper.toUserDto(userRepository.getById(id));
    }

    @Override
    public List<UserDtoRs> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserDto).toList();
    }

    @Override
    public UserDtoRs createUser(UserDtoRq user) {
        User newUser = userRepository.save(userMapper.toUser(user));
        return userMapper.toUserDto(newUser);
    }

    @Override
    public UserDtoRs updateUser(UserDtoRq user, Long id) {
        User oldUser = userRepository.getById(id);
        oldUser.setEmail(user.getEmail());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User updatedUser = userRepository.save(oldUser);
        return userMapper.toUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
