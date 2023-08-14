package pro.sky.todolist.exception;

public class UserTasksNotFoundException extends RuntimeException {
    private final long id;

    public UserTasksNotFoundException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Задачи у пользователя с id = " + id + " не найдены!";
    }

}

