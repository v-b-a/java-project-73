package hexlet.code.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class UserDtoOutput {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Date createdAt;
}
