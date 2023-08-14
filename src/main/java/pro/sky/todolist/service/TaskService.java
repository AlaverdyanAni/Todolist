package pro.sky.todolist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.todolist.dto.TaskDtoIn;
import pro.sky.todolist.dto.TaskDtoOut;
import pro.sky.todolist.exception.*;
import pro.sky.todolist.mapper.TaskMapper;
import pro.sky.todolist.model.Label;
import pro.sky.todolist.model.Task;
import pro.sky.todolist.model.User;
import pro.sky.todolist.repository.LabelRepository;
import pro.sky.todolist.repository.TaskRepository;
import pro.sky.todolist.repository.UserRepository;
import java.time.LocalDate;
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
        long userId = taskDtoIn.getUserId();
        long labelId = taskDtoIn.getLabelId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Label label = labelRepository.findById(labelId)
                .orElseThrow(()-> new LabelNotFoundException(labelId));

        Task task = taskMapper.toEntity(taskDtoIn);
        task.setUser(user);
        task.setLabel(label);
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
        long userId = taskDtoIn.getUserId();
        long labelId = taskDtoIn.getLabelId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Label label = labelRepository.findById(labelId)
                .orElseThrow(()-> new LabelNotFoundException(labelId));
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskMapper.enrichTask(taskDtoIn,task);
        task.setUser(user);
        task.setLabel(label);
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        List <Task> tasks =taskRepository.findTasksByUserId(user.getId());
        if(tasks.isEmpty()){
            throw new UserTasksNotFoundException(userId);
        }else
            return tasks
                    .stream()
                    .map(taskMapper::toDto)
                    .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskDtoOut> userAllTasksByLabel(long userId, long labelId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Label label = labelRepository.findById(labelId)
                .orElseThrow(()-> new LabelNotFoundException(labelId));
        List<Task> tasks = taskRepository.findTasksByUserIdAndLabelId(user.getId(),label.getId());
        if(tasks.isEmpty()){
            throw new UserTasksNotFoundException(userId);
        }else
            return tasks
                .stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

}

