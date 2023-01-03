package hexlet.code.app.controller;

import hexlet.code.app.dto.UserDtoOutput;
import hexlet.code.app.dto.UserDtoInput;
import hexlet.code.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static hexlet.code.app.controller.UserController.USER_CONTROLLER_PATH;

@RestController
@RequestMapping("${base-url}" + USER_CONTROLLER_PATH)
public class UserController {
    protected static final String USER_CONTROLLER_PATH = "/users";
    @Autowired
    private UserService userService;
//    @Autowired
//    private PasswordEncoder encoder;

    @GetMapping("/{id}")
    public UserDtoOutput getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("")
    public List<UserDtoOutput> getUsersList() {
        return userService.getUsersList();
    }

    @PostMapping("")
    public UserDtoOutput createUser(@Valid @RequestBody UserDtoInput userDtoInput) {
//        encodeUserPassword(userDtoInput);
        return userService.createUser(userDtoInput);
    }

    @PutMapping("/{id}")
    public UserDtoOutput updateUser(
            @Valid @RequestBody UserDtoInput userDtoInput,
            @PathVariable Long id
    ) {
//        encodeUserPassword(userDtoInput);
        return userService.updateUser(userDtoInput, id);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "Oks";
    }

//    private UserDtoInput encodeUserPassword(UserDtoInput userDtoInput) {
//        String password = userDtoInput.getPassword();
//        String encodedPassword = encoder.encode(password);
//        userDtoInput.setPassword(encodedPassword);
//        return userDtoInput;
//    }
}
