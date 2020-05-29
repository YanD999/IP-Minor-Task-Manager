package ucll.be.taskmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ucll.be.taskmanager.model.entity.SubTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SubTaskTest {
    private SubTask subTask;

    SubTaskTest() {
        subTask = new SubTask();
        subTask.setSubTitle("Task 1.1");
        subTask.setSubDescription("Vacuum the car");
    }

    @Test
    void getSubTitleReturnsCorrectSubTitle() {
        assertEquals("Task 1.1", subTask.getSubTitle());
    }

    @Test
    void getSubDescriptionReturnsCorrectSubDescription() {
        assertEquals("Vacuum the car", subTask.getSubDescription());
    }

    @Test
    void subTaskToStringIsCorrect() {
        assertEquals("id: 0, subTitle: Task 1.1, subDescription: Vacuum the car", subTask.toString());
    }
}
