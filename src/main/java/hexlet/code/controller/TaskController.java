package hexlet.code.controller;

import com.querydsl.core.types.Predicate;
import hexlet.code.dto.requestDto.TaskDtoRq;
import hexlet.code.dto.requestDto.TaskDtoRqUpdate;
import hexlet.code.dto.responseDto.TaskDtoRs;
import hexlet.code.repository.model.Task;
import hexlet.code.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hexlet.code.controller.TaskController.TASK_PATH;

@RestController
@RequestMapping("${base-url}" + TASK_PATH)
@AllArgsConstructor
public class TaskController {
    public static final String TASK_PATH = "/tasks";
    private final TaskService taskService;


    @Operation(summary = "Get task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task successfully found"),
            @ApiResponse(responseCode = "404", description = "Task is not found")
    })
    @GetMapping("/{id}")
    public TaskDtoRs getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id);
    }

    @Operation(summary = "Get tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks successfully found"),
            @ApiResponse(responseCode = "404", description = "Tasks is not found")
    })
    @GetMapping("")
    public List<TaskDtoRs> getTasks(@QuerydslPredicate(root = Task.class) final Predicate predicate) {
        return taskService.getTasks(predicate);
    }

    @Operation(summary = "Create task")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Tasks successfully created"))
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDtoRs createTask(@RequestBody @Valid TaskDtoRq taskDtoRq) {
        return taskService.createTask(taskDtoRq);
    }

    @Operation(summary = "Update task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task successfully updated"),
            @ApiResponse(responseCode = "404", description = "Tasks is not found")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDtoRs updateTask(@RequestBody @Valid TaskDtoRqUpdate taskDtoRq, @PathVariable("id") Long id) {
        return taskService.updateTask(taskDtoRq, id);
    }

    @Operation(summary = "Delete task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Tasks is not found")
    })
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }

}
