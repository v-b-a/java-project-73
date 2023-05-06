package hexlet.code.controller;

import hexlet.code.repository.dto.UserDtoRq;
import hexlet.code.repository.dto.UserDtoRs;
import hexlet.code.service.UserService;
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

    @GetMapping("/{id}")
    public UserDtoRs getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("")
    public List<UserDtoRs> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("")
    public UserDtoRs createUser(@RequestBody @Valid UserDtoRq user) {
        return  userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserDtoRs updateUser(@RequestBody @Valid UserDtoRq user, @PathVariable("id") Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
