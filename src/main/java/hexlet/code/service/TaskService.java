package hexlet.code.service;

import com.querydsl.core.types.Predicate;
import hexlet.code.dto.TaskDtoRq;
import hexlet.code.dto.TaskDtoRqUpdate;
import hexlet.code.dto.TaskDtoRs;
import hexlet.code.repository.StatusRepository;
import hexlet.code.repository.TaskRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.repository.model.Status;
import hexlet.code.repository.model.Task;
import hexlet.code.repository.model.User;
import hexlet.code.service.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final TaskMapper taskMapper;
    private final UserDetailsServiceImpl userDetailsService;

    public TaskDtoRs getTask(Long id) {
        return taskMapper.toTaskDtoRs(taskRepository.getById(id));
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
        return taskRepository.findAll().stream().map(taskMapper::toTaskDtoRs).toList();
    }

    public List<TaskDtoRs> getTasks(Predicate predicate) {
        return StreamSupport.stream(
                        taskRepository.findAll(predicate).spliterator(), true)
                .map(taskMapper::toTaskDtoRs)
                .collect(toList());
    }


    public TaskDtoRs createTask(TaskDtoRq taskDtoRq) {
        User author = userDetailsService.getCurrentUser();
        User executor = null;
        if (taskDtoRq.getExecutorId() != null) {
            executor = userRepository.findById(taskDtoRq.getExecutorId()).orElseThrow();
        }
        Status status = statusRepository.findById(taskDtoRq.getTaskStatusId()).orElseThrow();
        Task task = taskRepository.save(toTask(taskDtoRq, author, executor, status));
        return taskMapper.toTaskDtoRs(task);
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
        return taskMapper.toTaskDtoRs(newTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
