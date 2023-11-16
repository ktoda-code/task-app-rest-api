package ktoda.dev.swiftly.api.v1;

import ktoda.dev.swiftly.dto.status.StatusCreateRequest;
import ktoda.dev.swiftly.dto.status.StatusUpdateRequest;
import ktoda.dev.swiftly.model.Status;
import ktoda.dev.swiftly.service.StatusService;
import ktoda.dev.swiftly.service.TaskManagementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/statuses")
@AllArgsConstructor
@Slf4j
public class StatusRestController implements ApiInterface<StatusCreateRequest, StatusUpdateRequest, Long> {
    private final StatusService statusService;
    private final TaskManagementService managementService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(statusService.findAll());
    }

    @GetMapping("/{statusId}")
    public ResponseEntity<?> findById(@PathVariable("statusId") Long id) {
        return ResponseEntity.ok(statusService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody StatusCreateRequest entity) {
        Status status = new Status(entity.name());
        status.setTrackable(entity.trackable());
        return new ResponseEntity<>(statusService.save(status), HttpStatus.CREATED);
    }

    @PostMapping("/{statusId}/add/task/{taskId}")
    public ResponseEntity<?> addTask(@PathVariable Long statusId, @PathVariable Long taskId) {
        return new ResponseEntity<>(managementService.addTask(statusId, taskId), HttpStatus.CREATED);
    }

    @PostMapping("/{statusId}/remove/task/{taskId}")
    public ResponseEntity<?> removeTask(@PathVariable Long statusId, @PathVariable Long taskId) {
        return new ResponseEntity<>(managementService.removeTask(statusId, taskId), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody StatusUpdateRequest entity) {
        Status status = new Status(entity.name());
        status.setId(entity.id());
        status.setTrackable(entity.trackable());
        return ResponseEntity.ok(statusService.save(status));
    }

    @DeleteMapping("/{statusId}")
    public ResponseEntity<Void> delete(@PathVariable("statusId") Long id) {
        Status status = new Status();
        status.setId(id);
        statusService.remove(status);
        return ResponseEntity.ok().build();
    }
}
