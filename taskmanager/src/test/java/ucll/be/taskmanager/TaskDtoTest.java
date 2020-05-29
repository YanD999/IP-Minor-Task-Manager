package ucll.be.taskmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ucll.be.taskmanager.model.dto.SubTaskDTO;
import ucll.be.taskmanager.model.dto.TaskDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TaskDtoTest {
    private TaskDTO taskDto;

    TaskDtoTest() {
        taskDto = new TaskDTO();
        taskDto.setId(Long.parseLong("1"));
        taskDto.setTitle("Task 1");
        taskDto.setDescription("Wash the car");
        taskDto.setDate(LocalDateTime.of(2020, 3, 2, 13, 22));
    }

    @Test
    void taskEqualsTaskIsTrue() {
        assertTrue(taskDto.equals(taskDto));
    }

    @Test
    void setIdWorks() {
        taskDto.setId(Long.parseLong("2"));
        assertEquals(2, taskDto.getId());
        taskDto.setId(Long.parseLong("1"));
    }

    @Test
    void setTitleWorks() {
        taskDto.setTitle("Task2");
        assertEquals("Task2", taskDto.getTitle());
        taskDto.setTitle("Task1");
    }

    @Test
    void setDescriptionWorks() {
        taskDto.setDescription("Writing tests");
        assertEquals("Writing tests", taskDto.getDescription());
        taskDto.setDescription("Wash the car");
    }

    @Test
    void addSubtaskAddsASubtaskToTheTask() {
        taskDto.addSubTaskDto(new SubTaskDTO());
        assertEquals(1, taskDto.getSubTasksDto().size());
        taskDto.setSubTasksDto(new ArrayList<>());
    }

    @Test
    void getIdGivesCorrectId() {
        assertEquals(Long.parseLong("1"), taskDto.getId());
    }

    @Test
    void getTitleGivesCorrectTitle() {
        assertEquals("Task 1", taskDto.getTitle());
    }

    @Test
    void getDescriptionGivesCorrectDescription() {
        assertEquals("Wash the car", taskDto.getDescription());
    }

    @Test
    void getDateGivesCorrectDate() {
        assertEquals(LocalDateTime.of(2020, 3, 2, 13, 22), taskDto.getDate());
    }

    @Test
    void getSubTasksGivesAnEmptyArray() {
        assertEquals(0, taskDto.getSubTasksDto().size());
    }

    @Test
    void taskToStringGivesTask() {
        assertEquals("Id: 1, title: Task 1, description: Wash the car, date: 2020-03-02T13:22", taskDto.toString());
    }
}
