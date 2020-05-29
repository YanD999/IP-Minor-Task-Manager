package ucll.be.taskmanager.model.dto;

import org.springframework.format.annotation.DateTimeFormat;
import ucll.be.taskmanager.model.exception.DomainException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDTO {

    @NotNull
    private long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    private List<SubTaskDTO> subTasksDto;

    public TaskDTO() {
        setSubTasksDto(new ArrayList<>());
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.trim().isEmpty()) throw new DomainException("Title is null");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.trim().isEmpty()) throw new DomainException("Description is null.");
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        if (date == null) throw new DomainException("Date is null.");
        this.date = date;
    }

    public List<SubTaskDTO> getSubTasksDto() {
        return subTasksDto;
    }

    public void setSubTasksDto(List<SubTaskDTO> subTasksDto) {
        if (subTasksDto == null) throw new DomainException("SubTasks is null");
        else this.subTasksDto = subTasksDto;
    }

    public void addSubTaskDto(SubTaskDTO subTaskDTO) {
        if (subTaskDTO == null) throw new DomainException("SubTask is null");
        else this.subTasksDto.add(subTaskDTO);
    }

    public String dateToString() {
        if (date == null) return "";
        String month = date.getMonth().toString();
        return "due " + month.substring(0,1) + month.substring(1).toLowerCase() + " " + date.getDayOfMonth() + " " + date.getYear() + " at " + date.getHour() + (date.getMinute() == 0? "" : ":" + (date.getMinute() <= 10? "0" + date.getMinute() : date.getMinute())) + (date.getHour() > 12? " pm": " am");
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TaskDTO && this.getId() == ((TaskDTO) o).getId() && this.getTitle().equals(((TaskDTO) o).getTitle()) && this.getDescription().equals(((TaskDTO) o).getDescription()) && this.getDate().equals(((TaskDTO) o).getDate()) && this.getSubTasksDto().equals(((TaskDTO) o).getSubTasksDto());
    }

    @Override
    public String toString() {
        return "Id: " + getId() + ", title: " + getTitle() + ", description: " + getDescription() + ", date: " + getDate();
    }
}
