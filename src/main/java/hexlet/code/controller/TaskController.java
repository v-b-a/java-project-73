package hexlet.code.controller;

import com.querydsl.core.types.Predicate;
import hexlet.code.dto.TaskDtoRq;
import hexlet.code.dto.TaskDtoRqUpdate;
import hexlet.code.dto.TaskDtoRs;
import hexlet.code.repository.model.Task;
import hexlet.code.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static hexlet.code.controller.TaskController.TASK_PATH;

@RestController
@RequestMapping("${base-url}" + TASK_PATH)
@AllArgsConstructor
public class TaskController {
    public static final String TASK_PATH = "/tasks";
    private final TaskService taskService;


    @GetMapping("/{id}")
    public TaskDtoRs getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id);
    }

    @GetMapping("")
    public List<TaskDtoRs> getTasks(@QuerydslPredicate(root = Task.class) final Predicate predicate) {
        return predicate == null ? taskService.getTasks() : taskService.getTasks(predicate);
    }

    @PostMapping("")
    public TaskDtoRs createTask(@RequestBody @Valid TaskDtoRq taskDtoRq) {
        return taskService.createTask(taskDtoRq);
    }

    @PutMapping("/{id}")
    public TaskDtoRs updateTask(@RequestBody @Valid TaskDtoRqUpdate taskDtoRq, @PathVariable("id") Long id) {
        return taskService.updateTask(taskDtoRq, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }

}
