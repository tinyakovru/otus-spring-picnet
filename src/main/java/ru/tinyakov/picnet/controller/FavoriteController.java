package ru.tinyakov.picnet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.tinyakov.picnet.domain.Favorite;
import ru.tinyakov.picnet.service.FavoriteService;

@Controller
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/favorite/pic/{picId}")
    public ModelAndView addFavoritePic(@PathVariable long picId, Authentication authentication, Model model) {
        String nickname = authentication.getName();
        favoriteService.create(nickname,picId);
        return new ModelAndView("redirect:/pic/" + picId);
    }

    @DeleteMapping("/favorite/pic/{picId}")
    public ModelAndView removeFavoritePic(@PathVariable long picId, Authentication authentication, Model model) {
        String nickname = authentication.getName();
        favoriteService.delete(nickname,picId);
        return new ModelAndView("redirect:/pic/" + picId);
    }

}
