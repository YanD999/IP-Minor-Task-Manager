package ucll.be.taskmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ucll.be.taskmanager.model.dto.SubTaskDTO;
import ucll.be.taskmanager.model.dto.TaskDTO;
import ucll.be.taskmanager.model.service.TaskService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceTest {
    @Autowired
    private TaskService service;

    @Test
    @Transactional
    void addTask() {
        TaskDTO task = new TaskDTO();
        task.setId(1);
        task.setTitle("Task");
        task.setDescription("First task");
        task.setDate(LocalDateTime.of(2020, 1, 1, 23, 59));
        service.addTask(task);

        assertNotNull(service.getTask(1));
        assertTrue(task.equals(service.getTask(1)));
        assertFalse(service.getTasksDto().isEmpty());
        assertEquals(1, service.getTasksDto().size());
        assertEquals(2, service.getVolgendeId());
    }

    @Test
    @Transactional
    void addSubTask() {
        TaskDTO task = new TaskDTO();
        task.setId(1);
        task.setTitle("Task 1");
        task.setDescription("This is the first task");
        task.setDate(LocalDateTime.of(2020, 1, 1, 23, 59));

        SubTaskDTO subTaskDto = new SubTaskDTO();
        subTaskDto.setId(1);
        subTaskDto.setSubTitle("subtitle");
        subTaskDto.setSubDescription("subdescription");
        task.addSubTaskDto(subTaskDto);
        service.addTask(task);
        assertEquals(service.getTask(1).getSubTasksDto().size(), 1);
        assertNotNull(service.getTask(1).getSubTasksDto().get(0));
        assertEquals("subtitle", service.getTask(1).getSubTasksDto().get(0).getSubTitle());
    }

    @Test
    @Transactional
    void getTasksDto() {
        TaskDTO task = new TaskDTO();
        task.setId(1);
        task.setTitle("Task");
        task.setDescription("Description of task");
        task.setDate(LocalDateTime.of(2020, 1, 1, 23, 59));
        service.addTask(task);

        assertNotNull(service.getTasksDto());
        assertFalse(service.getTasksDto().isEmpty());
        assertEquals(1, service.getTasksDto().size());
        assertEquals("Description of task", service.getTask(1).getDescription());
    }

    @Test
    @Transactional
    void getTask() {
        TaskDTO task = new TaskDTO();
        task.setId(1);
        task.setTitle("Task 1");
        task.setDescription("First task");
        task.setDate(LocalDateTime.of(2020, 1, 1, 23, 59));
        service.addTask(task);

        assertNotNull(service.getTask(1));
        assertEquals(task, service.getTask(1));
        assertEquals("Task 1", service.getTask(1).getTitle());
        assertEquals(1, service.getTask(1).getId());
    }

    @Test
    void getVolgendeId() {
        assertEquals(1, service.getVolgendeId());
        assertEquals(service.getTasksDto().size() + 1, service.getVolgendeId());
        long volgende = service.getVolgendeId();
        assertEquals(volgende, service.getTasksDto().size() + 1);
    }

    @Test
    @Transactional
    void update() {
        TaskDTO task = new TaskDTO();
        task.setId(1);
        task.setTitle("Task 1");
        task.setDescription("First task");
        task.setDate(LocalDateTime.now());
        service.addTask(task);

        task.setTitle("Task 0");
        service.update(task);

        assertEquals("Task 0", service.getTask(1).getTitle());
    }

    @Test
    @Transactional
    void getSubTasks() {
        TaskDTO task = new TaskDTO();
        task.setId(1);
        task.setTitle("Title");
        task.setDescription("Description");
        task.setDate(LocalDateTime.of(2020, 1, 1, 23, 59));

        SubTaskDTO subTask = new SubTaskDTO();
        subTask.setSubTitle("sub");
        subTask.setSubDescription("Subd");
        task.addSubTaskDto(subTask);
        service.addTask(task);

        assertNotNull(service.getTask(1).getSubTasksDto());
        assertNotNull(service.getTask(1).getSubTasksDto().get(0));
        assertEquals("sub", service.getTask(1).getSubTasksDto().get(0).getSubTitle());
    }

    @Test
    @Transactional
    void getSubTasksDto() {
        TaskDTO task = new TaskDTO();
        task.setId(1);
        task.setTitle("Title");
        task.setDescription("Description");
        task.setDate(LocalDateTime.of(2020, 1, 1, 23, 59));

        SubTaskDTO subTask = new SubTaskDTO();
        subTask.setSubTitle("sub");
        subTask.setSubDescription("Subd");
        task.addSubTaskDto(subTask);
        service.addTask(task);

        assertNotNull(service.getTask(1).getSubTasksDto());
        assertEquals(1, service.getTask(1).getSubTasksDto().size());
        service.getTask(1).addSubTaskDto(new SubTaskDTO());
        assertEquals(1, service.getTask(1).getSubTasksDto().size());
    }

    @Test
    @Transactional
    void delete() {
        TaskDTO task = new TaskDTO();
        task.setId(1);
        task.setTitle("Task 1");
        task.setDescription("First task");
        task.setDate(LocalDateTime.now());
        service.addTask(task);
        assertEquals(1, service.getTasksDto().size());
        service.delete(1);
        assertEquals(0, service.getTasksDto().size());
        assertEquals(1, service.getVolgendeId());
    }
}
