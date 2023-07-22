package hexlet.code.service;

import hexlet.code.dto.requestDto.StatusDtoRq;
import hexlet.code.repository.StatusRepository;
import hexlet.code.repository.model.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatusService implements StatusServiceInterface {
    private final StatusRepository statusRepository;


    @Override
    public Status getStatus(Long id) {
        return statusRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public List<Status> getStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Status createStatus(StatusDtoRq statusDto) {
        Status status = Status.builder()
                .name(statusDto.getName())
                .build();
        return statusRepository.save(status);
    }

    @Override
    public Status updateStatus(StatusDtoRq statusDto, Long id) {
        Status currentStatus = statusRepository.getById(id);
        currentStatus.setName(statusDto.getName());
        return statusRepository.save(currentStatus);
    }

    @Override
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }
}
