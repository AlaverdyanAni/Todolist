package pro.sky.todolist.service;

import org.springframework.stereotype.Service;
import pro.sky.todolist.dto.LabelDto;
import pro.sky.todolist.exception.LabelIsPresentException;
import pro.sky.todolist.exception.LabelNotFoundException;
import pro.sky.todolist.mapper.LabelMapper;
import pro.sky.todolist.model.Label;
import pro.sky.todolist.repository.LabelRepository;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@Service
public class LabelService {
    private final LabelRepository labelRepository;
    private  final LabelMapper labelMapper;
    public LabelService(LabelRepository labelRepository, LabelMapper labelMapper) {
        this.labelRepository = labelRepository;
        this.labelMapper = labelMapper;
    }

    public LabelDto create(LabelDto labelDto) {
        String name = labelDto.getName();
        if (labelRepository.findByName(name).isPresent()) {
            throw new LabelIsPresentException(name);
        }
        return labelMapper.toDto(labelRepository.save(labelMapper.toEntity(labelDto)));
        }


    public LabelDto read(long id) {
        return labelMapper.toDto(
                labelRepository.findById(id)
                        .orElseThrow(() -> new LabelNotFoundException(id)));
    }

    public LabelDto update(long id, LabelDto labelDto) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new LabelNotFoundException(id));
        labelMapper.enrichLabel(labelDto, label);
        return labelMapper.toDto(labelRepository.save(label));
    }

    public LabelDto delete(long id) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new LabelNotFoundException(id));
        labelRepository.delete(label);
        return labelMapper.toDto(label);
    }
}
