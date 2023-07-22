package hexlet.code.service;

import hexlet.code.dto.requestDto.LabelDtoRq;
import hexlet.code.repository.model.Label;

import java.util.List;

public interface LabelServiceInterface {
    Label getLabel(Long id);

    List<Label> getLabels();

    Label createLabel(LabelDtoRq labelDtoRq);

    Label updateLabel(LabelDtoRq labelDtoRq, Long id);

    void deleteLabel(Long id);
}
