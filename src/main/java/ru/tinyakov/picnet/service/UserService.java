package ru.tinyakov.picnet.service;

import org.springframework.data.domain.Page;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.domain.dto.UserDto;
import ru.tinyakov.picnet.exception.InvalidDataException;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(long id);
    Page<User> getUserTop();
    User createUser(UserDto userDto) throws InvalidDataException;
}
