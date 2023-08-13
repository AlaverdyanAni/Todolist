package pro.sky.todolist.exception;

public class UserIsPresentException extends RuntimeException {
    private final String email;

    public UserIsPresentException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "Пользователь с email = " + email + " уже есть в БД!";
    }
}
