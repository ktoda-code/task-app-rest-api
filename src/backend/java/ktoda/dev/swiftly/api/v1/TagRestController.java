package ktoda.dev.swiftly.api.v1;

import ktoda.dev.swiftly.dto.tag.TagCreateRequest;
import ktoda.dev.swiftly.dto.tag.TagUpdateRequest;
import ktoda.dev.swiftly.model.Tag;
import ktoda.dev.swiftly.service.TagService;
import ktoda.dev.swiftly.service.impl.TaskManagementServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tags")
@AllArgsConstructor
@Slf4j
public class TagRestController implements ApiInterface<TagCreateRequest, TagUpdateRequest, Long> {
    private final TagService service;
    private final TaskManagementServiceImpl managementService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<?> findById(@PathVariable("tagId") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<?> findAllByTask(@PathVariable("taskId") Long id) {
        return ResponseEntity.ok(managementService.findAllTagsByTask(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TagCreateRequest entity) {
        Tag tag = new Tag(entity.name());
        return new ResponseEntity<>(service.save(tag), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TagUpdateRequest entity) {
        Tag tag = new Tag(entity.name());
        tag.setId(entity.id());
        return ResponseEntity.ok(service.save(tag));
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<?> delete(@PathVariable("tagId") Long id) {
        Tag tag = new Tag();
        tag.setId(id);
        service.remove(tag);
        return ResponseEntity.ok().build();
    }
}
