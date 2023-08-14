package pro.sky.todolist.exception;

public class UserHasTasksException extends RuntimeException {
    private  final long id;
    public UserHasTasksException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "У пользователя с id = " + id + " есть задачи!";
    }

}