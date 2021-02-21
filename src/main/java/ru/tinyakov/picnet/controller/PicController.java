package ru.tinyakov.picnet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.tinyakov.picnet.domain.PicnetUserPrincipal;
import ru.tinyakov.picnet.domain.Pic;
import ru.tinyakov.picnet.domain.Tag;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.domain.dto.PicInfoDto;
import ru.tinyakov.picnet.exception.PicNotFoundException;
import ru.tinyakov.picnet.service.PicService;
import ru.tinyakov.picnet.service.StorageService;
import ru.tinyakov.picnet.service.UserService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PicController {
    private final PicService picService;
    private final StorageService storageService;
    private final UserService userService;
    //GET ::
    //страница с картинками отсортированными по новизне, популярности sort=new,popular
    //pic/{sort}

    //страница с картинками по тегу
    //pic/tag/{id}
    @GetMapping("/pic/tag/{tagId}/pg/{pg}")
    public ModelAndView getPicsByTag(@PathVariable long tagId, @PathVariable int pg){
        Page<Pic> picsPage = picService.getPicsByTag(tagId,pg);
        ModelAndView modelAndView = new ModelAndView("pics");
        modelAndView.addObject("picsPage",picsPage);
        modelAndView.addObject("tagId",tagId);
        return modelAndView;
    }

    //страница картинок загруженных пользователем
    //pic/user/{id}
    //страница избранных картинок пользователя
    //pic/user/{id}/favorite

    //форма загрузки картинки
    //GET pic/add
    @GetMapping("/me/pic/add")
    public ModelAndView createPicLoadForm(/*Authentication authentication*/) {
//        storageService.store();
        //String nickname = authentication.getName();
        ModelAndView modelAndView = new ModelAndView("picLoadForm");

        return modelAndView;
    }

    //создание картинки
    //POST /me/pic/add
    @PostMapping("/me/pic/add")
    public ModelAndView addPic(@RequestParam("file") MultipartFile file, Authentication authentication) {
        log.info("POST addPic");
        String[] paths = storageService.store(file);


        if (paths == null)
            return new ModelAndView("err");
        PicnetUserPrincipal principal = (PicnetUserPrincipal) authentication.getPrincipal();

        Pic pic = picService.createPic(paths, principal.getUser());
        ModelAndView modelAndView = new ModelAndView("picEdit");
        modelAndView.addObject("file", paths[1]);
        modelAndView.addObject(pic);
        return modelAndView;
    }

    //удаление картинки
    //DELETE /me/pic/{id}

    //страница редактирования картинки
    //GET /me/pic/{id}/edit
    @GetMapping("/me/pic/{picId}/edit")
    public ModelAndView pagePicEdit(@PathVariable long picId, Authentication authentication) {
        log.info("GET pagePicEdit");
        PicnetUserPrincipal principal = (PicnetUserPrincipal) authentication.getPrincipal();
        Pic pic = picService.getPic(picId);
        ModelAndView modelAndView = new ModelAndView("picEdit");
        modelAndView.addObject(pic);
        modelAndView.addObject("alltags",
                pic.getTags().stream()
                        .map(Tag::getName)
                        .reduce("", (result, tag) -> result + " " + tag));
        return modelAndView;
    }

    //редактирование картинки
    //POST /me/pic/{id}/edit
    @PostMapping("/me/pic/{picId}/edit")
    public ModelAndView editPic(@PathVariable long picId, Authentication authentication, PicInfoDto picInfoDto) {
        log.info("POST editPic");
        try {
            PicnetUserPrincipal principal = (PicnetUserPrincipal) authentication.getPrincipal();
            Pic pic = picService.updatePic(picId, principal.getUser(), picInfoDto);
            ModelAndView modelAndView = new ModelAndView("redirect:/pic/" + picId);
//            modelAndView.addObject(pic);
            return modelAndView;
        } catch (PicNotFoundException e) {
            log.error(e.toString());
            return new ModelAndView("err");
        }
    }

    //модерация картинки
    //PUT /moder/pic/{id}

    //страница одной конкретной картинки
    //GET /pic/{id}
    @GetMapping("/pic/{id}")
    public ModelAndView getPic(@PathVariable long id, PicnetUserPrincipal principal) {
        Pic pic = picService.getPic(id);
        ModelAndView modelAndView = new ModelAndView("pic");
        modelAndView.addObject(pic);
        return modelAndView;
    }


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
