package pro.sky.todolist.service;

import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import pro.sky.todolist.model.Label;
import pro.sky.todolist.model.Task;
import pro.sky.todolist.model.User;
import pro.sky.todolist.repository.LabelRepository;
import pro.sky.todolist.repository.TaskRepository;
import pro.sky.todolist.repository.UserRepository;

@Service
public class GenerateService {

    private final Faker faker = new Faker();
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    private final TaskRepository taskRepository;


    public GenerateService(UserRepository userRepository, LabelRepository labelRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.labelRepository = labelRepository;
        this.taskRepository = taskRepository;
    }

    public void generateUsers(int count) {
        Stream.generate(this::generateUser)
                .limit(count)
                .forEach(userRepository::save);
    }

    public void generateLabels(int count) {
        Stream.generate(this::generateLabel)
                .limit(count)
                .forEach(labelRepository::save);
    }

    public void generateTasks(int count) {
        Stream.generate(this::generateTask)
                .limit(count)
                .forEach(taskRepository::save);
    }


    private User generateUser() {
        User user = new User();
        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        return user;
    }
    private Label generateLabel() {
        Label label = new Label();
        label.setName(faker.lorem().word());
        return label;
    }

    private Task generateTask() {
        Task task = new Task();
        task.setName(faker.lorem().word());
        task.setDescription(faker.lorem().sentence());
        task.setCreationDate(LocalDate.now());
        task.setUser(userRepository.findById(faker.random().nextLong(20)).get());
        task.setLabel(labelRepository.findById(faker.random().nextLong(20)).get());

        return task;
    }

}
