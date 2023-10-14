package pro.sky.todolist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import pro.sky.todolist.dto.LabelDto;
import pro.sky.todolist.model.Label;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LabelMapper {
    LabelDto toDto(Label label);
    @Mapping(target = "id", ignore = true)
    Label toEntity(LabelDto  labelDto);
    @Mapping(target = "id", ignore = true)
    void enrichLabel(LabelDto labelDto, @MappingTarget Label label);
}
