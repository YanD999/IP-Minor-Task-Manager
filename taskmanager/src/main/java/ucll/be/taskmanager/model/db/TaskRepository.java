package ucll.be.taskmanager.model.db;
import org.springframework.data.jpa.repository.JpaRepository;
import ucll.be.taskmanager.model.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
