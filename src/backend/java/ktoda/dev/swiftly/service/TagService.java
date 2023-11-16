package ktoda.dev.swiftly.service;

import ktoda.dev.swiftly.model.Tag;
import ktoda.dev.swiftly.model.Task;

import java.util.List;

public interface TagService extends Service<Tag, Long> {
    List<Tag> findAllByTask(Task task);

}
