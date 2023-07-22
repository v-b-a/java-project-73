package hexlet.code.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class TaskDtoRqUpdate {
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    @NotNull(message = "Status is required")
    private Long taskStatusId;
    private Long executorId;
    private List<Long> labelIds;
}
