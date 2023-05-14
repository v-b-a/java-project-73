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
        if (task.getExecutor() == null) {
            return TaskDtoRs.builder()
                    .id(task.getId())
                    .author(toUserDto(task.getAuthor()))
                    .taskStatus(task.getTaskStatus())
                    .name(task.getName())
                    .description(task.getDescription())
                    .createAt(task.getCreateAt())
                    .build();

        } else {
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
    }

    private Task toTask(TaskDtoRq taskDtoRq, User author, User executor, Status status) {
        return Task.builder()
                .name(taskDtoRq.getName())
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
            executor = userRepository.findById(taskDtoRq.getExecutorId()).orElseThrow();
        }
        Status status = statusRepository.findById(taskDtoRq.getTaskStatusId()).orElseThrow();
        Task task = taskRepository.save(toTask(taskDtoRq, author, executor, status));
        return toTaskDtoRs(task);
    }

    public TaskDtoRs updateTask(TaskDtoRqUpdate taskDtoRq, Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        User executor = null;
        if (taskDtoRq.getExecutorId() != null) {
            executor = userRepository.findById(taskDtoRq.getExecutorId()).orElseThrow();
        }
        Status status = statusRepository.findById(taskDtoRq.getTaskStatusId()).orElseThrow();
        task.setName(taskDtoRq.getName());
        task.setDescription(taskDtoRq.getDescription());
        task.setExecutor(executor);
        task.setTaskStatus(status);
        Task newTask = taskRepository.save(task);
        return toTaskDtoRs(newTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
