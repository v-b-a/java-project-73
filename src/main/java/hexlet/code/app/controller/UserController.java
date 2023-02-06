package hexlet.code.app.controller;

import hexlet.code.app.dto.UserDtoOutput;
import hexlet.code.app.dto.UserDtoInput;
import hexlet.code.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hexlet.code.app.controller.UserController.USER_CONTROLLER_PATH;

@RestController
@RequestMapping("${base-url}" + USER_CONTROLLER_PATH)
public class UserController {
    public static final String USER_CONTROLLER_PATH = "/users";
    @Autowired
    private UserService userService;

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
//        userDtoInput.setPassword(String.valueOf(userDtoInput.getPassword().hashCode()));
        return userService.createUser(userDtoInput);
    }

    @PutMapping("/{id}")
    public UserDtoOutput updateUser(
            @Valid @RequestBody UserDtoInput userDtoInput,
            @PathVariable Long id
    ) {
//        userDtoInput.setPassword(String.valueOf(userDtoInput.getPassword().hashCode()));
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
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
