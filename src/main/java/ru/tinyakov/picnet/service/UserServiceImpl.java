package ru.tinyakov.picnet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.domain.dto.UserDto;
import ru.tinyakov.picnet.exception.InvalidDataException;
import ru.tinyakov.picnet.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.of(userRepository.getOne(id));
    }

    @Override
    public Page<User> getUserTop() {
        return userRepository.findAll(PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "rating")));
    }

    @Override
    public User createUser(UserDto userDto) throws InvalidDataException {
        userDto.validate();
        log.info("userDto: {}",userDto.toString());
        User user = new User(userDto);
        log.info("user: {}",user.toString());

        return userRepository.save(user);
    }
}
