package ucll.be.taskmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ucll.be.taskmanager.model.dto.SubTaskDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SubTaskDtoTest {
    private SubTaskDTO subTaskDto;

    SubTaskDtoTest() {
        subTaskDto = new SubTaskDTO();
        subTaskDto.setSubTitle("Task 1.1");
        subTaskDto.setSubDescription("Vacuum the car");
    }

    @Test
    void getSubTitleReturnsCorrectSubTitle() {
        assertEquals("Task 1.1", subTaskDto.getSubTitle());
    }

    @Test
    void getSubDescriptionReturnsCorrectSubDescription() {
        assertEquals("Vacuum the car", subTaskDto.getSubDescription());
    }

    @Test
    void subTaskToStringIsCorrect() {
        assertEquals("Id: 0, subTitle: Task 1.1, subDescription: Vacuum the car", subTaskDto.toString());
    }
    
    @Test
    void equals() {
        assertTrue(subTaskDto.equals(subTaskDto));
    }

    @Test
    void notEquals() {
        assertFalse((subTaskDto.equals(new SubTaskDTO())));
    }
}
