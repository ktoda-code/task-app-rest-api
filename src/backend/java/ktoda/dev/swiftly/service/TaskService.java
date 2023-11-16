package ktoda.dev.swiftly.service;

import ktoda.dev.swiftly.model.Status;
import ktoda.dev.swiftly.model.Tag;
import ktoda.dev.swiftly.model.Task;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface TaskService extends Service<Task, Long> {
    List<Task> findAllByStatus(Status status);

    List<Task> findAllByTags(Set<Tag> tags);

    List<Task> findAllByCreatedOn(Date createdOn);

    Task create(Task entity);

    Task update(Task entity);

}
