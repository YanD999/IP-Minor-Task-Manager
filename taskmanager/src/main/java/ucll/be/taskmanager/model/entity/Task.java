package ucll.be.taskmanager.model.entity;

import org.springframework.format.annotation.DateTimeFormat;
import ucll.be.taskmanager.model.exception.DomainException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {
    @Id
    private long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SubTask> subTasks;

    public Task() {
        setSubTasks(new ArrayList<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id < 0) throw new DomainException("Id is invalid");
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.trim().isEmpty()) throw new DomainException("Title is empty");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.trim().isEmpty()) throw new DomainException("Description is leeg");
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        if (date == null) throw new DomainException("Local date time invalid");
        this.date = date;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public void addSubTask(SubTask subTask) {
        if (subTask == null) throw new DomainException("SubTask is null");
        subTasks.add(subTask);
    }

    @Override
    public String toString() {
        String out =  "Id: " + getId() + ", title: " + getTitle() + ", description: " + getDescription() + ", date: " + getDate();
        for (SubTask subTask: this.subTasks) {
            out += "\nsubtask: " + subTask.toString();
        }
        return out;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Task && this.getId() == (((Task) o).getId()) && this.getTitle().equals(((Task) o).getTitle()) && this.getDescription().equals(((Task) o).getDescription()) && this.getDate().equals(((Task) o).getDate()) && this.getSubTasks().equals(((Task) o).getSubTasks()));
    }
}
