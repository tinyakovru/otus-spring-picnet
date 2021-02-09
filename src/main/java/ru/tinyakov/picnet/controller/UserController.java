package ru.tinyakov.picnet.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.domain.UserDto;
import ru.tinyakov.picnet.exception.InvalidDataException;
import ru.tinyakov.picnet.repository.UserRepository;
import ru.tinyakov.picnet.service.UserService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/user/{id}")
    private String getUserById(@PathVariable long id, Model model) {
        log.info("req user id={}", id);
        Optional<User> oUser = userService.getUserById(id);
        if (oUser.isPresent()) {
            model.addAttribute(oUser.get());
            return "page";
        }
        System.out.println("getUserById return error");
        return "err";
    }

    @GetMapping(path = "/user/top", produces = "application/json")
    private @ResponseBody
    Page<User> getUserTop() {
        return userService.getUserTop();
    }

    @GetMapping(path = "/user/reg")
    private String getRegisterForm(){
        return "reg";
    }

    @PostMapping(path = "/user/reg")
    private @ResponseBody
    User createUser( @ModelAttribute("UserDto") UserDto userDto,
                    BindingResult result, ModelMap model) throws InvalidDataException {

//        if (result.hasErrors()) {
//            return "error";
//        }
//        model.addAttribute("name", employee.getName());
//        model.addAttribute("contactNumber", employee.getContactNumber());
//        model.addAttribute("id", employee.getId());
//        return "employeeView";

        User user = userService.createUser(userDto);
        return user;
        //model.addAllAttributes("user", user)
    }


}
