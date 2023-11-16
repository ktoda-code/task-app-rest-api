package ktoda.dev.swiftly.service.impl;

import ktoda.dev.swiftly.exception.status.StatusNotFoundException;
import ktoda.dev.swiftly.model.Status;
import ktoda.dev.swiftly.repository.StatusRepository;
import ktoda.dev.swiftly.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository repository;

    @Override
    public Status find(Status entity) {
        return repository.findOne(Example.of(entity))
                .orElseThrow(() ->
                        new StatusNotFoundException("Status not found for: " + entity));
    }

    @Override
    public Status findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new StatusNotFoundException("Status not found with id: " + id));
    }

    @Override
    public Status findByName(String statusName) {
        return repository.findByName(statusName)
                .orElseThrow(() ->
                        new StatusNotFoundException("Status not found with name: " + statusName));
    }

    @Override
    public List<Status> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean exists(Status entity) {
        return repository.exists(Example.of(entity));
    }

    @Override
    public Status save(Status entity) {
        return repository.save(entity);
    }

    @Override
    public void remove(Status entity) {
        Status status = find(entity);
        status.getTasks().forEach(task -> task.setStatus(null));
        repository.delete(status);
    }

}
