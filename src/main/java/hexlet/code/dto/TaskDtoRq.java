package hexlet.code.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class TaskDtoRq {
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    @NotNull(message = "Status is required")
    private Long taskStatusId;
    @NotNull(message = "Author is required")
    private Long authorId;
    private Long executorId;
}
