package ktoda.dev.swiftly.repository;

import ktoda.dev.swiftly.model.Tag;
import ktoda.dev.swiftly.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByTask(Task task);
}
