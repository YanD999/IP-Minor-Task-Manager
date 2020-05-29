package ucll.be.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ucll.be.taskmanager.model.db.TaskRepository;
import ucll.be.taskmanager.model.dto.SubTaskDTO;
import ucll.be.taskmanager.model.dto.TaskDTO;
import ucll.be.taskmanager.model.service.TaskService;
import ucll.be.taskmanager.model.service.TaskServiceImplementation;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskRepository repository) {
        service = new TaskServiceImplementation(repository);
    }

    @GetMapping("/")
    public String nav() {
        return "indexnav.html";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("taskList", service.getTasksDto());
        return "tasks";
    }

    @GetMapping("tasks/{id}")
    public String getDetail(Model model, @PathVariable(name = "id") String identifier) {
        TaskDTO task = null;
        try {
            int id = Integer.parseInt(identifier);
            task = service.getTask(id);
        } catch ( IndexOutOfBoundsException | EntityNotFoundException | NumberFormatException x) {
            System.out.println(x.getMessage());
        }
        model.addAttribute("task", task);
        return "detail";
    }

    @GetMapping("tasks/new")
    public String newTaskPage(Model model) {
        model.addAttribute("class", "current");
        model.addAttribute("id", service.getVolgendeId());
        return "new";
    }

    @PostMapping("tasks/new")
    public String makeNewTask(Model model, @ModelAttribute @Valid TaskDTO taskDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError fe: result.getFieldErrors()) {
                errors.add(fe.getDefaultMessage().split(":")[1]);
            }

            if (taskDto.getTitle() != null && !taskDto.getTitle().trim().isEmpty()) model.addAttribute("title", taskDto.getTitle());
            if (taskDto.getDescription() != null && !taskDto.getDescription().trim().isEmpty()) model.addAttribute("description", taskDto.getDescription());
            if (taskDto.getDate() != null) model.addAttribute("date", taskDto.getDate());
            model.addAttribute("id", taskDto.getId());
            model.addAttribute("errors", errors);
            return "new";
        }
        service.addTask(taskDto);
        return "redirect:/tasks";
    }

    @GetMapping("tasks/edit/{id}")
    public String edit(Model model, @PathVariable (name = "id") long id) {
        try {
            model.addAttribute("task", service.getTask(id));
        } catch (EntityNotFoundException x) {
            model.addAttribute("task", null);
        }
        return "edit";
    }

    @PostMapping("tasks/edit/{id}")
    public String edit(Model model, @PathVariable(name="id") long id, @ModelAttribute @Valid TaskDTO taskDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError fe: result.getFieldErrors()) {
                errors.add(fe.getDefaultMessage().split(":")[1]);
            }
            model.addAttribute("errors", errors);
            TaskDTO task = service.getTask(id);
            // correcte waarden invullen
            if (taskDto.getTitle() != null) task.setTitle(taskDto.getTitle());
            if (taskDto.getDescription() != null) task.setDescription(taskDto.getDescription());
            if (taskDto.getDate() != null) task.setDate(taskDto.getDate());
            model.addAttribute("task", task);
            return "edit";
        }
        else service.update(taskDto);
        return "redirect:/tasks/" + id;
    }

    @GetMapping("tasks/{id}/sub/create")
    public String subTask(Model model, @PathVariable(name = "id") long id) {
        TaskDTO task = service.getTask(id);
        model.addAttribute("task", task);
        return "subtask";
    }

    @PostMapping("tasks/{id}/sub/create")
    public String addSubTask(Model model, @PathVariable(name = "id") long id, @ModelAttribute @Valid SubTaskDTO subTaskDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError fe : result.getFieldErrors()) {
                errors.add(fe.getDefaultMessage().split(":")[1]);
            }
            if (subTaskDto.getSubTitle() != null && !subTaskDto.getSubTitle().trim().isEmpty()) model.addAttribute("subtitle", subTaskDto.getSubTitle());
            if (subTaskDto.getSubDescription() != null && !subTaskDto.getSubDescription().trim().isEmpty()) model.addAttribute("subdescription", subTaskDto.getSubDescription());
            model.addAttribute("task", service.getTask(id));
            model.addAttribute("errors", errors);
            return "detail";
        }
        service.addSubTask(id, subTaskDto);
        return "redirect:/tasks/" + id;
    }

    @GetMapping("tasks/delete/{id}")
    public String delete(@PathVariable(name = "id") long id) {
        service.delete(id);
        return "redirect:/tasks";
    }
}
