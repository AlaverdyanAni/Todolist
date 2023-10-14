package pro.sky.todolist.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pro.sky.todolist.model.Label;
import pro.sky.todolist.model.Status;
import pro.sky.todolist.model.User;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDtoOut {

    private Long id;

    private String name;

    private String description;

    private Status status;

    private LocalDate creationDate;

    private LocalDate updateDate;

    private long userId;

    private long labelId;
}
