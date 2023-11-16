package ktoda.dev.swiftly.service;

import ktoda.dev.swiftly.model.Status;
import ktoda.dev.swiftly.model.Tag;
import ktoda.dev.swiftly.model.Task;

import java.util.List;

public interface TaskManagementService {

    Status addTask(Long status, Long task);

    Status removeTask(Long status, Long task);

    Task addTag(Long task, Long tag);

    Task removeTag(Long task, Long tag);

    List<Tag> findAllTagsByTask(Long id);
}
