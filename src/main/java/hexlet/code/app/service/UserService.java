package hexlet.code.app.service;

import hexlet.code.app.dto.UserDtoOutput;
import hexlet.code.app.dto.UserDtoInput;

import java.util.List;

public interface UserService {
    UserDtoOutput getUserById(Long userId);

    List<UserDtoOutput> getUsersList();

    UserDtoOutput createUser(UserDtoInput userDto);

    UserDtoOutput updateUser(UserDtoInput userDtoInput, Long id);

    void deleteUserById(Long id);
}
