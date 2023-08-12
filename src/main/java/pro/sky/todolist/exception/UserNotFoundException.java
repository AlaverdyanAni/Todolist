package pro.sky.todolist.exception;

public class UserNotFoundException extends RuntimeException {
    private final long id;

    public UserNotFoundException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Пользователь с id = " + id + " не найден!";
    }
}
