package pro.sky.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.todolist.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT t.* FROM tasks t  WHERE t.user_id=?1", nativeQuery = true)
    List<Task> findTasksByUserId(Long id);
    @Query(value = "SELECT t.* FROM tasks t  WHERE t.user_id=?1 and t.label_id=?2", nativeQuery = true)
    List <Task> findTasksByUserIdAndLabelId(Long userId, Long labelId);
}