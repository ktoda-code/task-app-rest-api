package ktoda.dev.swiftly.service.impl;

import ktoda.dev.swiftly.exception.tag.TagNotFoundException;
import ktoda.dev.swiftly.model.Tag;
import ktoda.dev.swiftly.model.Task;
import ktoda.dev.swiftly.repository.TagRepository;
import ktoda.dev.swiftly.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository repository;

    @Override
    public Tag find(Tag entity) {
        return repository.findOne(Example.of(entity))
                .orElseThrow(() ->
                        new TagNotFoundException("Tag not found of: " + entity));
    }

    @Override
    public Tag findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new TagNotFoundException("Tag not found with id: " + id));
    }

    @Override
    public List<Tag> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean exists(Tag entity) {
        return repository.exists(Example.of(entity));
    }

    @Override
    public Tag save(Tag entity) {
        entity.setName(entity.getName().toLowerCase());
        return repository.save(entity);
    }

    @Override
    public void remove(Tag entity) {
        Tag tag = find(entity);
        repository.delete(tag);
    }

    @Override
    public List<Tag> findAllByTask(Task task) {
        return repository.findAllByTask(task);
    }
}
