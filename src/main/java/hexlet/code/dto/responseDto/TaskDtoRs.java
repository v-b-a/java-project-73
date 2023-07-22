package hexlet.code.dto.responseDto;

import hexlet.code.repository.model.Status;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class TaskDtoRs {
    private Long id;
    private String name;
    private String description;
    private Status taskStatus;
    private UserDtoRs author;
    private UserDtoRs executor;
    private Date createdAt;
    private List<String> labels;
}
