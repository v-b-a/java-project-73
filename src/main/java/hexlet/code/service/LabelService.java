package hexlet.code.service;

import hexlet.code.dto.LabelDtoRq;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.model.Label;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LabelService {
    private final LabelRepository labelRepository;

    public Label getLabel(Long id) {
        return labelRepository.findById(id).orElseThrow();
    }

    public List<Label> getLabels() {
        return labelRepository.findAll();
    }

    public Label createLabel(LabelDtoRq labelDtoRq) {
        Label label = new Label(labelDtoRq.getName());
        return labelRepository.save(label);
    }

    public Label updateLabel(LabelDtoRq labelDtoRq, Long id) {
        Label label = labelRepository.findById(id).orElseThrow();
        label.setName(labelDtoRq.getName());
        return labelRepository.save(label);
    }

    public void deleteLabel(Long id) {
        labelRepository.deleteById(id);
    }
}
