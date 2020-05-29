package ucll.be.taskmanager.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucll.be.taskmanager.model.exception.DomainException;
import ucll.be.taskmanager.model.db.TaskRepository;
import ucll.be.taskmanager.model.dto.SubTaskDTO;
import ucll.be.taskmanager.model.dto.TaskDTO;
import ucll.be.taskmanager.model.entity.SubTask;
import ucll.be.taskmanager.model.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImplementation implements TaskService {
    @Autowired
    private final TaskRepository repository;

    @Autowired
    public TaskServiceImplementation(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();

        task.setDescription(taskDTO.getDescription());
        task.setDate(taskDTO.getDate());
        task.setTitle(taskDTO.getTitle());
        task.setId(taskDTO.getId());
        task.setSubTasks(this.getSubTasks(taskDTO));
        repository.save(task);
    }

    @Override
    public void addSubTask(long id, SubTaskDTO subTaskDTO) {
        Task task = repository.getOne(id);
        if (subTaskDTO == null) throw new DomainException("SubTaskDto is null");
        SubTask subTask = new SubTask();
        subTask.setId(subTaskDTO.getId());
        subTask.setSubTitle(subTaskDTO.getSubTitle());
        subTask.setSubDescription(subTaskDTO.getSubDescription());
        // toevoegen
        task.addSubTask(subTask);
        // update
        repository.save(task);
    }

    @Override
    public List<TaskDTO> getTasksDto() {
        return repository.findAll().stream().map(h -> {
            TaskDTO dto = new TaskDTO();
            dto.setDescription(h.getDescription());
            dto.setDate(h.getDate());
            dto.setTitle(h.getTitle());
            if (h.getSubTasks() != null) dto.setSubTasksDto(this.getSubTasksDto(h.getSubTasks()));
            dto.setId(h.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTask(long id) {
        TaskDTO dto = new TaskDTO();
        Task task = repository.getOne(id);

        dto.setId(id);
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDate(task.getDate());
        dto.setSubTasksDto(this.getSubTasksDto(task.getSubTasks()));
        return dto;
    }

    @Override
    public long getVolgendeId() {
        List<Task> tasks = repository.findAll();
        if (tasks == null || tasks.size() == 0) return 1;
        long hoogsteId = 0;
        for (Task t: tasks) {
            if (t.getId() > hoogsteId) hoogsteId = t.getId();
        }
        return ++hoogsteId;
    }

    @Override
    public void update(TaskDTO taskDTO) {
        if (taskDTO == null) throw new DomainException("TaskDto is null");

        Task task = repository.getOne(taskDTO.getId());

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDate(taskDTO.getDate());
        // update opgeroepen door addSubTask, dus taskDTO heeft subtasks
        if (taskDTO.getSubTasksDto().size() != 0) task.setSubTasks(getSubTasks(taskDTO));
        repository.save(task);
    }

    // van SubTaskDTO naar SubTask
    private List<SubTask> getSubTasks(TaskDTO taskDTO) {
        List<SubTask> subTasks = new ArrayList<>();
        if (taskDTO.getSubTasksDto() == null) return subTasks;
        for (SubTaskDTO s: taskDTO.getSubTasksDto()) {
            SubTask subTask = new SubTask();
            subTask.setId(s.getId());
            subTask.setSubTitle(s.getSubTitle());
            subTask.setSubDescription(s.getSubDescription());
            subTasks.add(subTask);
        }
        return subTasks;
    }

    // van SubTask naar SubTaskDTO
    private List<SubTaskDTO> getSubTasksDto(List<SubTask> subTasks) {
        List<SubTaskDTO> subTasksDto = new ArrayList<>();
        if (subTasks == null) return subTasksDto;
        for (SubTask s: subTasks) {
            SubTaskDTO subTaskDto = new SubTaskDTO();
            subTaskDto.setId(s.getId());
            subTaskDto.setSubTitle(s.getSubTitle());
            subTaskDto.setSubDescription(s.getSubDescription());
            subTasksDto.add(subTaskDto);
        }
        return subTasksDto;
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
