package hexlet.code.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

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
