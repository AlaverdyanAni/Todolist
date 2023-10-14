package pro.sky.todolist.exception;

public class TaskNotFoundException extends RuntimeException{
    private final long id;

    public TaskNotFoundException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Задача с id = " + id + " не найдена!";
    }

}

