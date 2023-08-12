package pro.sky.todolist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import pro.sky.todolist.dto.UserDto;
import pro.sky.todolist.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toDto(User user);
    @Mapping(target = "id",ignore = true)
    User toEntity(UserDto userDto);
    @Mapping(target = "id", ignore = true)
    void enrichUser(UserDto userDto, @MappingTarget User user);

}