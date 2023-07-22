package hexlet.code.dto.responseDto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDtoRs {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Date createdAt;
}
