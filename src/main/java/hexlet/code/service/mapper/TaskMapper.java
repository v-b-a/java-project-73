package hexlet.code.service.mapper;

import hexlet.code.dto.TaskDtoRs;
import hexlet.code.repository.model.Label;
import hexlet.code.repository.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskMapper {
    private final UserMapper userMapper;

    public TaskDtoRs toTaskDtoRs(Task task) {
        return TaskDtoRs.builder()
                .id(task.getId())
                .author(userMapper.toUserDto(task.getAuthor()))
                .executor(userMapper.toUserDto(task.getExecutor()))
                .taskStatus(task.getTaskStatus())
                .name(task.getName())
                .description(task.getDescription())
                .createdAt(task.getCreateAt())
                .labels(task.getLabel() == null ? null : task.getLabel().stream()
                                .map(Label::getName)
                                .collect(Collectors.toList()))
                .build();
    }
}
