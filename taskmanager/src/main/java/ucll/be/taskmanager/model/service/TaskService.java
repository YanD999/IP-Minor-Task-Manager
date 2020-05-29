package ucll.be.taskmanager.model.service;

import ucll.be.taskmanager.model.dto.SubTaskDTO;
import ucll.be.taskmanager.model.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    void addTask(TaskDTO taskDTO);
    void addSubTask(long id, SubTaskDTO subTaskDTO);
    List<TaskDTO> getTasksDto();
    TaskDTO getTask(long id);
    long getVolgendeId();
    void update(TaskDTO taskDTO);
    void delete(long id);
}
