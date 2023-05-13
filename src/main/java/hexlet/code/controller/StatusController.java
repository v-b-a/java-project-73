package hexlet.code.controller;

import hexlet.code.dto.StatusDtoRq;
import hexlet.code.repository.model.Status;
import hexlet.code.service.StatusService;
import lombok.AllArgsConstructor;
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

import static hexlet.code.controller.StatusController.STATUS_PATH;

@RestController
@RequestMapping("${base-url}" + STATUS_PATH)
@AllArgsConstructor
public class StatusController {
    public static final String STATUS_PATH = "/statuses";
    private final StatusService statusService;

    @GetMapping("/{id}")
    public Status getStatus(@PathVariable("id") Long id) {
       return statusService.getStatus(id);
    }

    @GetMapping("")
    public List<Status> getStatuses() {
        return statusService.getStatuses();
    }

    @PostMapping("")
    public Status createStatus(@RequestBody @Valid StatusDtoRq statusDto) {
        return statusService.createStatus(statusDto);
    }

    @PutMapping("/{id}")
    public Status updateStatus(@RequestBody @Valid StatusDtoRq statusDto, @PathVariable Long id) {
        return statusService.updateStatus(statusDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteStatus(@PathVariable("id") Long id) {
        statusService.deleteStatus(id);
    }
}
