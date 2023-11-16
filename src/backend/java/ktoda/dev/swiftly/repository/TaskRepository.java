package ktoda.dev.swiftly.repository;

import ktoda.dev.swiftly.model.Status;
import ktoda.dev.swiftly.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, TaskCustomRepository {
    List<Task> findAllByStatus(Status status);

    List<Task> findAllByCreatedOn(Date createdOn);
}
