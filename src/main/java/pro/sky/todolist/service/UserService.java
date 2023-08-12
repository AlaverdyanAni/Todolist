package pro.sky.todolist.service;

import org.springframework.stereotype.Service;
import pro.sky.todolist.dto.UserDto;
import pro.sky.todolist.exception.UserNotFoundException;
import pro.sky.todolist.mapper.UserMapper;
import pro.sky.todolist.model.User;
import pro.sky.todolist.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto create(UserDto userDto) {
        return userMapper.toDto(
                userRepository.save(
                        userMapper.toEntity(userDto)
                )
        );
    }


    public UserDto read(long id) {
        return userMapper.toDto(
                userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id))
        );
    }

    public UserDto update(long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userMapper.enrichUser(userDto, user);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto delete(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
        return userMapper.toDto(user);
    }

    public List<UserDto> list() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

}

