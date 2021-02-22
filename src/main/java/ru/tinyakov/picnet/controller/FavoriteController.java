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
import ru.tinyakov.picnet.domain.Favorite;
import ru.tinyakov.picnet.domain.PicnetUserPrincipal;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.service.FavoriteService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/favorite/pic/{picId}")
    public ModelAndView addFavoritePic(@PathVariable long picId, Authentication authentication, Model model) {
        log.info("POST favorite ");
        String nickname = authentication.getName();
        favoriteService.create(nickname,picId);
        return new ModelAndView("redirect:/pic/" + picId);
    }

    @DeleteMapping("/favorite/pic/{picId}")
    public ModelAndView removeFavoritePic(@PathVariable long picId, Authentication authentication, Model model) {
        log.info("DELETE favorite ");
        if(authentication == null) return new ModelAndView("err");

        PicnetUserPrincipal principal = (PicnetUserPrincipal)authentication.getPrincipal();
        User user = principal.getUser();
        favoriteService.delete(user.getId(),picId);
        return new ModelAndView("redirect:/pic/" + picId);
    }

}
