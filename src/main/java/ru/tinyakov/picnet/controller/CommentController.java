package ru.tinyakov.picnet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.tinyakov.picnet.service.CommentService;
import ru.tinyakov.picnet.service.FavoriteService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/pic/{picId}")
    public ModelAndView addCommentPic(@PathVariable long picId, Authentication authentication, Model model) {
//        String nickname = authentication.getName();
//        favoriteService.create(nickname,picId);
        log.info("POST addCommentPic");
        return new ModelAndView("redirect:/pic/" + picId);
    }

    @DeleteMapping("/comment/pic/{picId}")
    public ModelAndView removeCommentPic(@PathVariable long picId, Authentication authentication, Model model) {
        String nickname = authentication.getName();
        log.info("DELETE removeCommentPic, nicknamekname={}, picId={}",nickname,picId);
        commentService.deleteComment(nickname,picId);
        return new ModelAndView("redirect:/pic/" + picId);
    }

}
