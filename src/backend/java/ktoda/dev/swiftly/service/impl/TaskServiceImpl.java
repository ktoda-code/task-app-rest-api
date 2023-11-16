package ktoda.dev.swiftly.service.impl;

import ktoda.dev.swiftly.exception.task.TaskNotFoundException;
import ktoda.dev.swiftly.model.Status;
import ktoda.dev.swiftly.model.Tag;
import ktoda.dev.swiftly.model.Task;
import ktoda.dev.swiftly.repository.TaskRepository;
import ktoda.dev.swiftly.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    @Override
    public Task find(Task entity) {
        return repository.findOne(Example.of(entity))
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found of: " + entity));
    }

    @Override
    public Task findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id));
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean exists(Task entity) {
        return repository.exists(Example.of(entity));
    }

    @Override
    public Task create(Task entity) {
        entity.setCreatedOn(new Date(System.currentTimeMillis()));
        return save(entity);
    }

    @Override
    public Task save(Task entity) {
        return repository.save(entity);
    }

    @Override
    public Task update(Task entity) {
        entity.setUpdatedOn(new Date(System.currentTimeMillis()));
        return save(entity);
    }

    @Override
    public void remove(Task entity) {
        Task task = find(entity);
        repository.delete(task);
    }

    @Override
    public List<Task> findAllByStatus(Status status) {
        return repository.findAllByStatus(status);
    }

    @Override
    public List<Task> findAllByTags(Set<Tag> tags) {
        return repository.findAllByTags(tags);
    }

    @Override
    public List<Task> findAllByCreatedOn(Date createdOn) {
        return repository.findAllByCreatedOn(createdOn);
    }
}
