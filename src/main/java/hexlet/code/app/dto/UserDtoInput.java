package hexlet.code.app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDtoInput {
    @NotNull
    @Size(min = 1)
    private String firstName;
    @NotNull
    @Size(min = 1)
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 3)
    private String password;
}
