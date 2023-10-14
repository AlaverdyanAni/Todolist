package pro.sky.todolist.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pro.sky.todolist.dto.UserDto;
import pro.sky.todolist.exception.UserHasTasksException;
import pro.sky.todolist.exception.UserIsPresentException;
import pro.sky.todolist.exception.UserNotFoundException;
import pro.sky.todolist.mapper.UserMapper;
import pro.sky.todolist.model.Task;
import pro.sky.todolist.model.User;
import pro.sky.todolist.repository.TaskRepository;
import pro.sky.todolist.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, TaskRepository taskRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.userMapper = userMapper;
    }
    @Transactional
    public UserDto create(UserDto userDto) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            throw new UserIsPresentException(userDto.getEmail());
        } else {
            User createdUser = userRepository.save(userMapper.toEntity(userDto));
            return userMapper.toDto(createdUser);
        }
    }

    @Transactional
    public UserDto read(long id) {
        return userMapper.toDto(
                userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Transactional
    public UserDto update(long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userMapper.enrichUser(userDto, user);
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public UserDto delete(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        List<Task> tasks = taskRepository.findTasksByUserId(id);
        if (!tasks.isEmpty()) {
            throw new UserHasTasksException(id);
        } else {
            userRepository.delete(user);
            return userMapper.toDto(user);
        }
    }

}

