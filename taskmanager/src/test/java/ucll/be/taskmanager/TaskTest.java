package ucll.be.taskmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ucll.be.taskmanager.model.entity.SubTask;
import ucll.be.taskmanager.model.entity.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskTest {
    private Task task;

    TaskTest() {
        task = new Task();
        task.setId(Long.parseLong("1"));
        task.setTitle("Task 1");
        task.setDescription("Wash the car");
        task.setDate(LocalDateTime.of(2020, 3, 2, 13, 22));
    }

    @Test
    void taskEqualsTaskIsTrue() {
        assertTrue(task.equals(task));
    }

    @Test
    void setIdWorks() {
        task.setId(Long.parseLong("2"));
        assertEquals(2, task.getId());
        task.setId(Long.parseLong("1"));
    }

    @Test
    void setTitleWorks() {
        task.setTitle("Task2");
        assertEquals("Task2", task.getTitle());
        task.setTitle("Task1");
    }

    @Test
    void setDescriptionWorks() {
        task.setDescription("Writing tests");
        assertEquals("Writing tests", task.getDescription());
        task.setDescription("Wash the car");
    }

    @Test
    void addSubtaskAddsASubtaskToTheTask() {
        task.addSubTask(new SubTask());
        assertEquals(1, task.getSubTasks().size());
        task.setSubTasks(new ArrayList<>());
    }

    @Test
    void getIdGivesCorrectId() {
        assertEquals(Long.parseLong("1"), task.getId());
    }

    @Test
    void getTitleGivesCorrectTitle() {
        assertEquals("Task 1", task.getTitle());
    }

    @Test
    void getDescriptionGivesCorrectDescription() {
        assertEquals("Wash the car", task.getDescription());
    }

    @Test
    void getDateGivesCorrectDate() {
        assertEquals(LocalDateTime.of(2020, 3, 2, 13, 22), task.getDate());
    }

    @Test
    void getSubTasksGivesAnEmptyArray() {
        assertEquals(0, task.getSubTasks().size());
    }

    @Test
    void taskToStringGivesTask() {
        assertEquals("Id: 1, title: Task 1, description: Wash the car, date: 2020-03-02T13:22", task.toString());
    }
}
