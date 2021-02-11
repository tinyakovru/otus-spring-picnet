package ru.tinyakov.picnet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.tinyakov.picnet.config.PicnetUserPrincipal;
import ru.tinyakov.picnet.domain.Pic;
import ru.tinyakov.picnet.service.PicService;

@Controller
@RequiredArgsConstructor
public class PicController {
    private final PicService picService;
    //GET ::
    //страница с новыми картинками
    //pic/new
    //страница с популярными картинками
    //pic/popular
    //страница с картинками по тегу
    //pic/tag/{id}
    //страница картинок загруженных пользователем
    //pic/user/{id}
    //страница избранных картинок пользователя
    //pic/user/{id}/favorite

    //форма загрузки картинки
    //GET pic/add
    //создание картинки
    //POST /pic/add
    //удаление картинки
    //DELETE /pic/{id}
    //модерация картинки
    //PUT /pic/{id}/edit
    //страница одной конкретной картинки
    //GET /pic/{id}
    @GetMapping("/pic/{id}")
    public ModelAndView getPic(@PathVariable long id, PicnetUserPrincipal principal) {
        Pic pic = picService.getPic(id);
        ModelAndView modelAndView = new ModelAndView("pic");

        modelAndView.addObject("picUrl", pic.getUrlS());
        modelAndView.addObject("picId", pic.getId());
        modelAndView.addObject("auth", principal.getUser() != null);

        return modelAndView;
    }

    //страница редактирования картинки
    //GET /pic/{id}/edit

    ////////////////////////////////////////////////////
    //лайк картинки
    //POST /pic/{id}/favorite
//    @PostMapping("/pic/{pid}/favorite")
//    public ModelAndView addFavoritePic(@PathVariable long pid, Authentication authentication, Model model) {
//        String nickname = authentication.getName();
//        picService.addFavorite(pid, nickname);
//        return new ModelAndView("redirect:/pic/" + pid);
//    }
}
