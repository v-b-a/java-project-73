package hexlet.code.service;

import com.querydsl.core.types.Predicate;
import hexlet.code.dto.requestDto.TaskDtoRq;
import hexlet.code.dto.requestDto.TaskDtoRqUpdate;
import hexlet.code.dto.responseDto.TaskDtoRs;
import hexlet.code.repository.model.Label;
import hexlet.code.repository.model.Status;
import hexlet.code.repository.model.Task;
import hexlet.code.repository.model.User;

import java.util.List;

public interface TaskServiceInterface {
    TaskDtoRs getTask(Long id);

    default Task toTask(TaskDtoRq taskDtoRq, User author, User executor, Status status, List<Label> labels) {
        return Task.builder()
                .name(taskDtoRq.getName())
                .author(author)
                .executor(executor)
                .taskStatus(status)
                .description(taskDtoRq.getDescription())
                .labels(labels)
                .build();
    }

    List<TaskDtoRs> getTasks();

    List<TaskDtoRs> getTasks(Predicate predicate);

    TaskDtoRs createTask(TaskDtoRq taskDtoRq);

    TaskDtoRs updateTask(TaskDtoRqUpdate taskDtoRq, Long id);

    void deleteTask(Long id);
}
