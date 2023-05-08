package hexlet.code.service;

import hexlet.code.dto.StatusDtoRq;
import hexlet.code.repository.StatusRepository;
import hexlet.code.repository.model.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;


    public Status getStatus(Long id) {
        return statusRepository.findById(id).orElseThrow();
    }

    public List<Status> getStatuses() {
        return statusRepository.findAll();
    }

    public Status createStatus(StatusDtoRq statusDto) {
        return statusRepository.save(
                Status.builder()
                        .name(statusDto.getName())
                        .build());
    }

    public Status updateStatus(StatusDtoRq statusDto, Long id) {
        Status currentStatus = statusRepository.getById(id);
        currentStatus.setName(statusDto.getName());
        return statusRepository.save(currentStatus);
    }

    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }
}
