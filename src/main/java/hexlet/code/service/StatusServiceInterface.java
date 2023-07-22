package hexlet.code.service;

import hexlet.code.dto.requestDto.StatusDtoRq;
import hexlet.code.repository.model.Status;

import java.util.List;

public interface StatusServiceInterface {
    Status getStatus(Long id);

    List<Status> getStatuses();

    Status createStatus(StatusDtoRq statusDto);

    Status updateStatus(StatusDtoRq statusDto, Long id);

    void deleteStatus(Long id);
}
