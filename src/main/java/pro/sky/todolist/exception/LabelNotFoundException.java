package pro.sky.todolist.exception;

public class LabelNotFoundException extends RuntimeException {
        private final long id;

        public LabelNotFoundException(long id) {
            this.id = id;
        }

        @Override
        public String getMessage() {
            return "Категория с id = " + id + " не найдена!";
        }
    }

