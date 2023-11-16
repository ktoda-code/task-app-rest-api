package ktoda.dev.swiftly.service.impl;

import ktoda.dev.swiftly.model.Status;
import ktoda.dev.swiftly.model.Tag;
import ktoda.dev.swiftly.model.Task;
import ktoda.dev.swiftly.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskManagementServiceImpl implements TaskManagementService {
    private final TaskService taskService;
    private final TagService tagService;
    private final StatusService statusService;

    @Override
    public Status addTask(Long status, Long task) {
        Task managedTask = taskService.findById(task);
        Status managedStatus = statusService.findById(status);
        managedTask.setStatus(managedStatus);
        managedStatus.addTask(managedTask);
        taskService.save(managedTask);
        return statusService.save(managedStatus);
    }

    @Override
    public Status removeTask(Long status, Long task) {
        Task managedTask = taskService.findById(task);
        Status managedStatus = statusService.findById(status);
        if (managedStatus.getTasks().contains(managedTask)) {
            managedTask.setStatus(null);
            managedStatus.removeTask(managedTask);
        }
        taskService.save(managedTask);
        return statusService.save(managedStatus);
    }

    @Override
    public Task addTag(Long task, Long tag) {
        Tag managedTag = tagService.findById(tag);
        Task managedTask = taskService.findById(task);
        managedTask.addTag(managedTag);
        managedTag.setTask(managedTask);
        tagService.save(managedTag);
        return taskService.save(managedTask);
    }

    @Override
    public Task removeTag(Long task, Long tag) {
        Tag managedTag = tagService.findById(tag);
        Task managedTask = taskService.findById(task);
        managedTask.removeTag(managedTag);
        managedTag.setTask(null);
        tagService.save(managedTag);
        return taskService.save(managedTask);
    }

    @Override
    public List<Tag> findAllTagsByTask(Long id) {
        Task task = taskService.findById(id);
        return tagService.findAllByTask(task);
    }
}
