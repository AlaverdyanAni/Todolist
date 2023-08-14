package pro.sky.todolist.exception;

public class LabelHasTasksException extends RuntimeException {
    private  final long id;
    public LabelHasTasksException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "По категории с id = " + id + " есть задачи!";
    }

}