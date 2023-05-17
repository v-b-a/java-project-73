package hexlet.code.controller;

import hexlet.code.dto.UserDtoRq;
import hexlet.code.dto.UserDtoRs;
import hexlet.code.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static hexlet.code.controller.UserController.USERS_PATH;

@RestController
@RequestMapping("${base-url}" + USERS_PATH)
@AllArgsConstructor
public class UserController {
    public static final String USERS_PATH = "/users";
    private final UserService userService;

    @Operation(summary = "Get a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully found"),
            @ApiResponse(responseCode = "404", description = "User is not found")
    })
    @GetMapping("/{id}")
    public UserDtoRs getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Get users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users successfully found"),
            @ApiResponse(responseCode = "404", description = "Users is not found")
    })
    @GetMapping("")
    public List<UserDtoRs> getUsers() {
        return userService.getUsers();
    }

    @Operation(summary = "Create user")
    @ApiResponse(responseCode = "200", description = "User created")
    @PostMapping("")
    public UserDtoRs createUser(@RequestBody @Valid UserDtoRq user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "404", description = "Users is not found")
    })
    @PutMapping("/{id}")
    public UserDtoRs updateUser(@RequestBody @Valid UserDtoRq user, @PathVariable("id") Long id) {
        return userService.updateUser(user, id);
    }

    @Operation(summary = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Users is not found")
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
