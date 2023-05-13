package hexlet.code.service;

import hexlet.code.dto.TaskDtoRq;
import hexlet.code.dto.TaskDtoRqUpdate;
import hexlet.code.dto.TaskDtoRs;
import hexlet.code.dto.UserDtoRs;
import hexlet.code.repository.StatusRepository;
import hexlet.code.repository.TaskRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.repository.model.Status;
import hexlet.code.repository.model.Task;
import hexlet.code.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    public TaskDtoRs getTask(Long id) {
        return toTaskDtoRs(taskRepository.getById(id));
    }

    private UserDtoRs toUserDto(User user) {
        return UserDtoRs.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createAt(user.getCreatedAt())
                .build();
    }

    private TaskDtoRs toTaskDtoRs(Task task) {
        return TaskDtoRs.builder()
                .id(task.getId())
                .author(toUserDto(task.getAuthor()))
                .executor(toUserDto(task.getExecutor()))
                .taskStatus(task.getTaskStatus())
                .name(task.getName())
                .description(task.getDescription())
                .createAt(task.getCreateAt())
                .build();
    }

    private Task toTask(TaskDtoRq taskDtoRq, User author, User executor, Status status) {
        return Task.builder()
                .author(author)
                .executor(executor)
                .taskStatus(status)
                .description(taskDtoRq.getDescription())
                .build();
    }

    public List<TaskDtoRs> getTasks() {
        return taskRepository.findAll().stream().map(this::toTaskDtoRs).toList();
    }


    public TaskDtoRs createTask(TaskDtoRq taskDtoRq) {
        User author = userRepository.getById(taskDtoRq.getAuthorId());
        User executor = null;
        if (taskDtoRq.getExecutorId() != null) {
            executor = userRepository.getById(taskDtoRq.getExecutorId());
        }
        Status status = statusRepository.getById(taskDtoRq.getTaskStatusId());
        return toTaskDtoRs(taskRepository.save(toTask(taskDtoRq, author, executor, status)));
    }

    public TaskDtoRs updateTask(TaskDtoRqUpdate taskDtoRq, Long id) {
        Task task = taskRepository.getById(id);
        User executor = null;
        if (taskDtoRq.getExecutorId() != null) {
            executor = userRepository.getById(taskDtoRq.getExecutorId());
        }
        Status status = statusRepository.getById(taskDtoRq.getTaskStatusId());
        task.setName(taskDtoRq.getName());
        task.setDescription(taskDtoRq.getDescription());
        task.setExecutor(executor);
        task.setTaskStatus(status);
        return toTaskDtoRs(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
