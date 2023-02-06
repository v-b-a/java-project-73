package hexlet.code.app.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 3)
    private String password;
}
