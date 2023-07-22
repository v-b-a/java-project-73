package hexlet.code.service;

import hexlet.code.dto.requestDto.UserDtoRq;
import hexlet.code.dto.responseDto.UserDtoRs;

import java.util.List;

public interface UserServiceInterface {
    UserDtoRs getUserById(Long id);

    List<UserDtoRs> getUsers();

    UserDtoRs createUser(UserDtoRq user);

    UserDtoRs updateUser(UserDtoRq user, Long id);

    void deleteUser(Long id);
}
