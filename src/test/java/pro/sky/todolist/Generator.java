package pro.sky.todolist;

import com.github.javafaker.Faker;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import pro.sky.todolist.dto.LabelDto;
import pro.sky.todolist.dto.TaskDtoIn;
import pro.sky.todolist.dto.UserDto;
import pro.sky.todolist.model.Label;
import pro.sky.todolist.model.Task;
import pro.sky.todolist.model.User;


@SpringBootTest
public abstract class Generator {

    protected final Faker faker = new Faker();

    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER =
            new PostgreSQLContainer<>(
                    "postgres:15")
                    .withUsername("ani")
                    .withPassword("1234")
                    .withExposedPorts(5432)
                    .withDatabaseName("bookstore");

    static {
        POSTGRES_CONTAINER.start();
        int postgresPort = POSTGRES_CONTAINER.getMappedPort(5432);
        System.setProperty(
                "spring.datasource.url",
                "jdbc:postgresql://localhost:" + postgresPort + "/todolist"
        );
        System.setProperty(
                "spring.liquibase.url",
                "jdbc:postgresql://localhost:" + postgresPort + "/todolist"
        );
    }

    @PreDestroy
    public void onExit() {
        POSTGRES_CONTAINER.stop();
    }

    protected User generateUser() {
        User user = new User();
        user.setId(faker.random().nextLong(10));
        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        return user;
    }

    protected UserDto generateUserDto() {
        UserDto userDto = new UserDto() ;
        userDto.setName(faker.name().firstName());
        userDto.setEmail(faker.internet().emailAddress());

        return userDto;
    }

    protected Label generateLabel() {
        Label label = new Label();
        label.setId(faker.random().nextLong(20));
        label.setName(faker.lorem().word());

        return label;
    }

    protected LabelDto generateLabelDto() {
        LabelDto labelDto = new LabelDto();
        labelDto.setName(faker.lorem().word());
        return labelDto;
    }

    protected Task generateTask() {
        Task task = new Task();
        task.setId(faker.random().nextLong(20));
        task.setName(faker.lorem().word());
        task.setDescription(faker.lorem().sentence());
        task.setUser(generateUser());
        task.setLabel(generateLabel());

        return task;
    }

    protected TaskDtoIn generateTaskDtoIn() {
       TaskDtoIn taskDtoIn = new TaskDtoIn();
        taskDtoIn.setName(faker.lorem().word());
        taskDtoIn.setDescription(faker.lorem().sentence());
        taskDtoIn.setUserId(faker.random().nextLong(10));
        taskDtoIn.setLabelId(faker.random().nextLong(20));

        return taskDtoIn;
    }

}
