package hexlet.code.app.service;

import hexlet.code.app.dao.UserRepository;
import hexlet.code.app.dto.UserDtoOutput;
import hexlet.code.app.dto.UserDtoInput;
import hexlet.code.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDtoOutput getUserById(Long userId) {
        User user = userRepository.findUserById(userId);
        return fillUserDto(user);
    }

    @Override
    public List<UserDtoOutput> getUsersList() {
        return userRepository.findAll().stream().map(this::fillUserDto).toList();
    }

    @Override
    public UserDtoOutput createUser(UserDtoInput userDto) {
        userRepository.save(fillUser(userDto));
        User newUser = userRepository.findUserByFirstNameAndLastNameAndEmail(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail());
        return fillUserDto(newUser);
    }

    @Override
    public UserDtoOutput updateUser(UserDtoInput userDtoInput, Long id) {
        User user = fillUser(userDtoInput);
        user.setCreatedAt(userRepository.findUserById(id).getCreatedAt());
        user.setId(id);
        userRepository.save(user);
        return fillUserDto(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private UserDtoOutput fillUserDto(User user) {
        UserDtoOutput userDtoOutput = new UserDtoOutput();
        userDtoOutput.setId(user.getId());
        userDtoOutput.setEmail(user.getEmail());
        userDtoOutput.setFirstName(user.getFirstName());
        userDtoOutput.setLastName(user.getLastName());
        userDtoOutput.setCreatedAt(user.getCreatedAt());
        return userDtoOutput;
    }
    private User fillUser(UserDtoInput userDtoInput) {
        User user = new User();
        user.setFirstName(userDtoInput.getFirstName());
        user.setLastName(userDtoInput.getLastName());
        user.setEmail(userDtoInput.getEmail());
        user.setPassword(userDtoInput.getPassword());
        return user;
    }
}
