package ucll.be.taskmanager.rest.controller;

import org.springframework.web.bind.annotation.*;
import ucll.be.taskmanager.model.dto.TaskDTO;
import ucll.be.taskmanager.model.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {
    private final TaskService service;

    public TaskRestController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/tasks")
    @ResponseBody
    public List<TaskDTO> getTasks() {
        return service.getTasksDto();
    }

    @PostMapping("/tasks")
    public TaskDTO createNewTask(@RequestBody @Valid TaskDTO taskDto) {
        service.addTask(taskDto);
        return taskDto;
    }
}
