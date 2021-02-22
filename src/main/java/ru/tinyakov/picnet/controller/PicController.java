package ru.tinyakov.picnet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.tinyakov.picnet.domain.*;
import ru.tinyakov.picnet.domain.dto.PicInfoDto;
import ru.tinyakov.picnet.domain.dto.PicPageDto;
import ru.tinyakov.picnet.exception.InvalidPicSortException;
import ru.tinyakov.picnet.exception.PicNotFoundException;
import ru.tinyakov.picnet.service.PicService;
import ru.tinyakov.picnet.service.StorageService;
import ru.tinyakov.picnet.service.TagService;
import ru.tinyakov.picnet.service.UserService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PicController {
    private final PicService picService;
    private final StorageService storageService;
    private final UserService userService;
    private final TagService tagService;

    //страница одной конкретной картинки
    //GET /pic/{id}
    @GetMapping("/pic/{id}")
    public ModelAndView getPic(@PathVariable long id, Authentication authentication) {
        PicPageDto picPage = picService.getPic(id, authentication);
//        Pic pic = picService.getPic(id);
        ModelAndView modelAndView = new ModelAndView("pic");
        modelAndView.addObject(picPage);
//        boolean isLiked = false;
//        if(authentication != null) {
//            PicnetUserPrincipal principal = (PicnetUserPrincipal) authentication.getPrincipal();
//            Favorite favorite = new Favorite(principal.getUser().getId(), pic.getId());
//            isLiked = (principal != null) && pic.getFavorites().contains(favorite);
//        }
//        log.info("isLiked={}",isLiked);
//        modelAndView.addObject("isLiked", isLiked);
        return modelAndView;
    }

    //GET ::
    //страница с картинками отсортированными по новизне, популярности sort=new,popular
    //pic/{sort}
    @GetMapping("/pic/{sort}/pg/{pg}")
    public ModelAndView getPicsByTag(@PathVariable String sort, @PathVariable int pg){
        try {
            Page<Pic> picsPage = picService.getPics(sort,pg);
            ModelAndView modelAndView = new ModelAndView("pics");
            modelAndView.addObject("picsPage",picsPage);
            modelAndView.addObject("titlePage",sort+" pics");
            return modelAndView;
        } catch (InvalidPicSortException e) {
            log.error(e.toString());
            return new ModelAndView("err");
        }
    }

    //страница с картинками по тегу
    //pic/tag/{id}
    @GetMapping("/pic/tag/{tagId}/pg/{pg}")
    public ModelAndView getPicsByTag(@PathVariable long tagId, @PathVariable int pg){
        Tag tag = tagService.getTag(tagId);
        Page<Pic> picsPage = picService.getPicsByTag(tagId,pg);
        ModelAndView modelAndView = new ModelAndView("pics");
        modelAndView.addObject("picsPage",picsPage);
        modelAndView.addObject("titlePage",tag.getName());
        return modelAndView;
    }

    //страница картинок загруженных пользователем
    //pic/user/{id}
    @GetMapping("/pic/user/{userId}/pg/{pg}")
    public ModelAndView getPicsByUser(@PathVariable long userId, @PathVariable int pg){
        User user = userService.getUserById(userId).orElseThrow();
        Page<Pic> picsPage = picService.getPicsByUser(user,pg);
        ModelAndView modelAndView = new ModelAndView("pics");
        modelAndView.addObject("picsPage",picsPage);
        modelAndView.addObject("titlePage",user.getNickname()+"'s pictures");
        return modelAndView;
    }

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
        Optional<Pic> optPic = picService.getMyPic(picId, authentication);
        if(optPic.isPresent()) {
            Pic pic = optPic.get();
            ModelAndView modelAndView = new ModelAndView("picEdit");
            modelAndView.addObject(pic);
            modelAndView.addObject("alltags",
                    pic.getTags().stream()
                            .map(Tag::getName)
                            .reduce("", (result, tag) -> result + " " + tag));
            return modelAndView;
        }
        return new ModelAndView("err");
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
