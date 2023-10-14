package pro.sky.todolist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import pro.sky.todolist.dto.LabelDto;
import pro.sky.todolist.dto.TaskDtoIn;
import pro.sky.todolist.dto.TaskDtoOut;
import pro.sky.todolist.model.Label;
import pro.sky.todolist.model.Task;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "labelId", source = "label.id")
    TaskDtoOut toDto(Task task);
    Task toEntity(TaskDtoIn taskDtoIn);
    @Mapping(target = "id", ignore = true)
    void enrichTask(TaskDtoIn taskDtoIn, @MappingTarget Task task);




}

