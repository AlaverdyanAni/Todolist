package pro.sky.todolist.exception;

public class LabelIsPresentException extends RuntimeException{
    private final String name;

    public LabelIsPresentException(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "Категория с названием name = " + name + " уже есть в БД!";
    }

}

