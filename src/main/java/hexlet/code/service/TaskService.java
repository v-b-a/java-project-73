package hexlet.code.service;

import com.querydsl.core.types.Predicate;
import hexlet.code.dto.requestDto.TaskDtoRq;
import hexlet.code.dto.requestDto.TaskDtoRqUpdate;
import hexlet.code.dto.responseDto.TaskDtoRs;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.StatusRepository;
import hexlet.code.repository.TaskRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.repository.model.Label;
import hexlet.code.repository.model.Status;
import hexlet.code.repository.model.Task;
import hexlet.code.repository.model.User;
import hexlet.code.controller.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class TaskService implements TaskServiceInterface {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final LabelRepository labelRepository;
    private final TaskMapper taskMapper;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public TaskDtoRs getTask(Long id) {
        return taskMapper.toTaskDtoRs(taskRepository.getById(id));
    }

    @Override
    public List<TaskDtoRs> getTasks() {
        return taskRepository.findAll().stream().map(taskMapper::toTaskDtoRs).toList();
    }

    @Override
    public List<TaskDtoRs> getTasks(Predicate predicate) {
        return StreamSupport.stream(
                        taskRepository.findAll(predicate).spliterator(), true)
                .map(taskMapper::toTaskDtoRs)
                .collect(toList());
    }


    @Override
    public TaskDtoRs createTask(TaskDtoRq taskDtoRq) {
        User author = userDetailsService.getCurrentUser();
        User executor = null;
        if (taskDtoRq.getExecutorId() != null) {
            executor = userRepository.findById(taskDtoRq.getExecutorId()).orElseThrow();
        }
        Status status = statusRepository.findById(taskDtoRq.getTaskStatusId()).orElseThrow();

        List<Label> labels = taskDtoRq.getLabelIds() != null
                ? labelRepository.findAllById(taskDtoRq.getLabelIds()) : null;
        Task task = taskRepository.save(toTask(taskDtoRq, author, executor, status, labels));
        return taskMapper.toTaskDtoRs(task);
    }

    @Override
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
        List<Label> labels = task.getLabels();
        if (taskDtoRq.getLabelIds() != null && !taskDtoRq.getLabelIds().isEmpty()) {
            List<Label> rqLabels = labelRepository.findAllById(taskDtoRq.getLabelIds());
            for (Label rqLabel: rqLabels) {
                if (!labels.contains(rqLabel)) {
                    labels.add(rqLabel);
                }
            }
            labels.removeIf(label -> !rqLabels.contains(label));
        }
        task.setLabels(labels);

        Task newTask = taskRepository.save(task);
        return taskMapper.toTaskDtoRs(newTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
