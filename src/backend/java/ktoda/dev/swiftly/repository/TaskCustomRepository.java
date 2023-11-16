package ktoda.dev.swiftly.repository;

import ktoda.dev.swiftly.model.Tag;
import ktoda.dev.swiftly.model.Task;

import java.util.List;
import java.util.Set;

public interface TaskCustomRepository {
    List<Task> findAllByTags(Set<Tag> tags);
}
