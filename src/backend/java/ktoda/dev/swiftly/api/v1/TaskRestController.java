package ktoda.dev.swiftly.api.v1;

import ktoda.dev.swiftly.dto.task.TaskCreateRequest;
import ktoda.dev.swiftly.dto.task.TaskUpdateRequest;
import ktoda.dev.swiftly.model.Status;
import ktoda.dev.swiftly.model.Tag;
import ktoda.dev.swiftly.model.Task;
import ktoda.dev.swiftly.service.StatusService;
import ktoda.dev.swiftly.service.TagService;
import ktoda.dev.swiftly.service.TaskManagementService;
import ktoda.dev.swiftly.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
@Slf4j
public class TaskRestController implements ApiInterface<TaskCreateRequest, TaskUpdateRequest, Long> {
    private final TaskService taskService;
    private final StatusService statusService;
    private final TagService tagService;
    private final TaskManagementService managementService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> findById(@PathVariable("taskId") Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @GetMapping("/status")
    public ResponseEntity<?> findAllByStatusName(@RequestParam(name = "name") String statusName) {
        log.info("Requesting status: {}", statusName);
        Status status = statusService.findByName(statusName);
        return ResponseEntity.ok(taskService.findAllByStatus(status));
    }

    @GetMapping("/tags")
    public ResponseEntity<?> findAllByTagNames(@RequestParam(name = "names") Set<String> tagNames) {
        log.info("Requesting tags: {}", tagNames);
        List<Tag> tags = tagService.findAll().stream()
                .filter(tag -> tagNames.contains(tag.getName()))
                .toList();
        return ResponseEntity.ok(taskService.findAllByTags(new HashSet<>(tags)));
    }

    @GetMapping("/date")
    public ResponseEntity<?> findAllByDate(@RequestParam(name = "created") Date createdOn) {
        log.info("Requesting date: {}", createdOn);
        return ResponseEntity.ok(taskService.findAllByCreatedOn(createdOn));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TaskCreateRequest entity) {
        Task task = new Task(entity.title(), entity.description());
        return new ResponseEntity<>(taskService.create(task), HttpStatus.CREATED);
    }

    @PostMapping("/{taskId}/add/tag/{tagId}")
    public ResponseEntity<?> addTag(@PathVariable Long taskId, @PathVariable Long tagId) {
        return new ResponseEntity<>(managementService.addTag(taskId, tagId), HttpStatus.CREATED);
    }

    @PostMapping("/{taskId}/remove/tag/{tagId}")
    public ResponseEntity<?> removeTag(@PathVariable Long taskId, @PathVariable Long tagId) {
        return new ResponseEntity<>(managementService.removeTag(taskId, tagId), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TaskUpdateRequest entity) {
        Task task = taskService.findById(entity.id());
        task.setTitle(entity.title());
        task.setDescription(entity.description());
        return ResponseEntity.ok(taskService.update(task));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> delete(@PathVariable("taskId") Long id) {
        Task task = new Task();
        task.setId(id);
        taskService.remove(task);
        return ResponseEntity.ok().build();
    }
}
