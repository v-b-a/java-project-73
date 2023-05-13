package hexlet.code.dto;

import hexlet.code.repository.model.Status;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskDtoRs {
    private Long id;
    private String name;
    private String description;
    private Status taskStatus;
    private UserDtoRs author;
    private UserDtoRs executor;
    private Date createAt;
}
