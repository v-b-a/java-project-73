package hexlet.code.service;

import hexlet.code.dto.requestDto.LabelDtoRq;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.model.Label;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LabelService implements LabelServiceInterface {
    private final LabelRepository labelRepository;

    @Override
    public Label getLabel(Long id) {
        return labelRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Label> getLabels() {
        return labelRepository.findAll();
    }

    @Override
    public Label createLabel(LabelDtoRq labelDtoRq) {
        Label label = new Label(labelDtoRq.getName());
        return labelRepository.save(label);
    }

    @Override
    public Label updateLabel(LabelDtoRq labelDtoRq, Long id) {
        Label label = labelRepository.findById(id).orElseThrow();
        label.setName(labelDtoRq.getName());
        return labelRepository.save(label);
    }

    @Override
    public void deleteLabel(Long id) {
        labelRepository.deleteById(id);
    }
}
