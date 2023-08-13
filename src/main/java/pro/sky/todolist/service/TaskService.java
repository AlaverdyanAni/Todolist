package pro.sky.todolist.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.todolist.dto.TaskDtoIn;
import pro.sky.todolist.dto.TaskDtoOut;
import pro.sky.todolist.exception.TaskNotFoundException;
import pro.sky.todolist.mapper.TaskMapper;
import pro.sky.todolist.model.Task;
import pro.sky.todolist.repository.LabelRepository;
import pro.sky.todolist.repository.TaskRepository;
import pro.sky.todolist.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    private final TaskMapper taskMapper;


    public TaskService(TaskRepository taskRepository, UserRepository userRepository, LabelRepository labelRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.labelRepository = labelRepository;
        this.taskMapper = taskMapper;
    }

    @Transactional
    public TaskDtoOut create(TaskDtoIn taskDtoIn) {
        Task task = taskMapper.toEntity(taskDtoIn);
        task.setCreationDate(LocalDate.now());
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Transactional(readOnly = true)
    public TaskDtoOut read(long id) {
        return taskMapper.toDto(
                taskRepository.findById(id)
                        .orElseThrow(() -> new TaskNotFoundException(id)));
    }

    @Transactional
    public TaskDtoOut update(long id, TaskDtoIn taskDtoIn) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setUpdateDate(LocalDate.now());
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Transactional
    public TaskDtoOut delete(long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
        return taskMapper.toDto(task);
    }

    @Transactional(readOnly = true)
    public List<TaskDtoOut> userAllTasks(long userId) {
        return taskRepository.findTasksByUserId(userId)
                .stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskDtoOut> userAllTasksByLabel(long userId, long labelId) {
        return taskRepository.findTasksByUserIdAndLabelId(userId,labelId)
                .stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

}

