package pro.sky.todolist.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pro.sky.todolist.dto.LabelDto;
import pro.sky.todolist.dto.UserDto;
import pro.sky.todolist.exception.*;
import pro.sky.todolist.mapper.LabelMapper;
import pro.sky.todolist.model.Label;
import pro.sky.todolist.model.Task;
import pro.sky.todolist.model.User;
import pro.sky.todolist.repository.LabelRepository;
import pro.sky.todolist.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@Service
public class LabelService {
    private final LabelRepository labelRepository;
    private final TaskRepository taskRepository;

    private  final LabelMapper labelMapper;

    public LabelService(LabelRepository labelRepository, TaskRepository taskRepository, LabelMapper labelMapper) {
        this.labelRepository = labelRepository;
        this.taskRepository = taskRepository;
        this.labelMapper = labelMapper;
    }
    @Transactional
    public LabelDto create(LabelDto labelDto) {
        Optional<Label> label = labelRepository.findById(labelDto.getId());
        if (label.isPresent()) {
            throw new LabelIsPresentException(labelDto.getName());
        } else {
            Label createdLabel = labelRepository.save(labelMapper.toEntity(labelDto));
            return labelMapper.toDto(createdLabel);
        }
    }
    @Transactional
    public LabelDto read(long id) {
        return labelMapper.toDto(
                labelRepository.findById(id)
                        .orElseThrow(() -> new LabelNotFoundException(id)));
    }
    @Transactional
    public LabelDto update(long id, LabelDto labelDto) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new LabelNotFoundException(id));
        labelMapper.enrichLabel(labelDto, label);
        return labelMapper.toDto(labelRepository.save(label));
    }
    @Transactional
    public LabelDto delete(long id) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new LabelNotFoundException(id));
        List<Task> tasks = taskRepository.findTasksByLabelId(id);
        if (!tasks.isEmpty()) {
           throw new LabelHasTasksException(id);
        } else
            labelRepository.delete(label);
        return labelMapper.toDto(label);
    }

}
