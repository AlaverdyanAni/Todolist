package pro.sky.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.todolist.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

