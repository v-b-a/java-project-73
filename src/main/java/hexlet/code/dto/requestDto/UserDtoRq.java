package hexlet.code.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDtoRq {
    @NotBlank(message = "email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @NotBlank(message = "password is required")
    @Size(min = 3, message = "Password length should be between min 3")
    private String password;
}
