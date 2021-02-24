package ru.tinyakov.picnet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.domain.dto.UserDto;
import ru.tinyakov.picnet.exception.InvalidDataException;
import ru.tinyakov.picnet.service.UserService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/user/top", produces = "application/json")
    private @ResponseBody
    Page<User> getUserTop() {
        return userService.getUserTop();
    }

    @GetMapping(path = "/user/reg")
    private String getRegisterForm(){
        log.info("getRegisterForm");
        return "reg";
    }

    @PostMapping(path = "/user/reg")
    private @ResponseBody
    User createUser( @ModelAttribute("UserDto") UserDto userDto,
                    BindingResult result, ModelMap model) throws InvalidDataException {
        User user = userService.createUser(userDto);
        return user;
    }


}
