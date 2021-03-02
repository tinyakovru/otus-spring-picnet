package ru.tinyakov.picnet.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.tinyakov.picnet.domain.Pic;
import ru.tinyakov.picnet.service.PicService;

@Controller
@RequiredArgsConstructor
public class DefaultController {

    private final PicService picService;

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/admin")
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView("admin");
        val oPic = picService.getPicForModer();
        oPic.ifPresent(pic -> modelAndView.addObject("pic",pic));
        return modelAndView;
    }
}
