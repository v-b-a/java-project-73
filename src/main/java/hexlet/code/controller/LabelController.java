package hexlet.code.controller;

import hexlet.code.dto.LabelDtoRq;
import hexlet.code.repository.model.Label;
import hexlet.code.service.LabelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hexlet.code.controller.LabelController.LABEL_PATH;

@RestController
@RequestMapping("${base-url}" + LABEL_PATH)
@AllArgsConstructor
public class LabelController {
    public static final String LABEL_PATH = "/labels";
    private final LabelService labelService;

    @GetMapping("/{id}")
    public Label getLabel(@PathVariable("id") Long id) {
        return labelService.getLabel(id);
    }

    @GetMapping("")
    public List<Label> getLabels() {
        return labelService.getLabels();
    }

    @PostMapping("")
    public Label createLabel(@RequestBody LabelDtoRq labelDtoRq) {
        return labelService.createLabel(labelDtoRq);
    }

    @PutMapping("/{id}")
    public Label updateLabel(@RequestBody LabelDtoRq labelDtoRq, @PathVariable("id") Long id) {
        return labelService.updateLabel(labelDtoRq, id);
    }

    @DeleteMapping("/{id}")
    public void deleteLabel(@PathVariable("id") Long id) {
        labelService.deleteLabel(id);
    }
}
