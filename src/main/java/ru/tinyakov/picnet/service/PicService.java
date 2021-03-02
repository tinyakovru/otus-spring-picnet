package ru.tinyakov.picnet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import ru.tinyakov.picnet.domain.*;
import ru.tinyakov.picnet.domain.dto.PicInfoDto;
import ru.tinyakov.picnet.domain.dto.PicPageDto;
import ru.tinyakov.picnet.exception.InvalidPicSortException;
import ru.tinyakov.picnet.exception.PicNotFoundException;
import ru.tinyakov.picnet.repository.CommentRepository;
import ru.tinyakov.picnet.repository.PicRepository;
import ru.tinyakov.picnet.repository.TagRepository;
import ru.tinyakov.picnet.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PicService {
    @Value("${picnet.pic-on-page}")
    private int picOnPage;

    private final PicRepository picRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final TagService tagService;
    private final FavoriteService favoriteService;

    public PicPageDto getPic(long id, Authentication authentication) {
        PicPageDto picPage = new PicPageDto();
        Pic pic = picRepository.getOne(id);
        User owner = pic.getUserOwner();
        picPage.setPic(pic);

        boolean isLiked = false;

        if (authentication != null) {
            PicnetUserPrincipal principal = (PicnetUserPrincipal) authentication.getPrincipal();
            picPage.setAuth(true);
            Optional<Favorite> optionalFavorite = favoriteService.get(principal.getUser().getId(), pic.getId());
            if (optionalFavorite.isPresent())
                isLiked = true;
        }
        picPage.setLiked(isLiked);
        picPage.setNickname(owner.getNickname());
        picPage.setUserId(owner.getId());
        picPage.setLikeCount(favoriteService.getCountByPicId(pic.getId()));
        return picPage;
    }

    public Pic createPic(String[] paths, User user) {
        Pic pic = new Pic();
        pic.setUrlL(paths[0]);
        pic.setUrlS(paths[1]);
        pic.setUrlXS(paths[2]);
        pic.setUserOwner(user);
        return picRepository.save(pic);
    }

    public Pic updatePic(long picId, User user, PicInfoDto picInfoDto) throws PicNotFoundException {
        log.info("updatePic");

        Pic pic = picRepository.findById(picId).orElseThrow(PicNotFoundException::new);
        pic.setTitle(picInfoDto.getTitle());
        pic.setDescr(picInfoDto.getDescr());
        tagService.updateTags(pic, user, picInfoDto.getTags());
        return pic;
    }

    public Page<Pic> getPicsByTag(long tagId, int page) {
        Pageable pageable = PageRequest.of(page - 1, picOnPage);
        Tag tag = tagRepository.getOne(tagId);
        return picRepository.findByTagsContainingAndStatus(tag, 1, pageable);

    }

    public Page<Pic> getPics(String sort, int page) throws InvalidPicSortException {
        log.info("getPics: sort={}", sort);
        Pageable pageable = PageRequest.of(page - 1, picOnPage);
        if (sort.equals("new"))
            return picRepository.findByStatusOrderByIdDesc(1, pageable);
        if (sort.equals("popular"))
            return picRepository.findByStatusOrderByRatingDesc(1, pageable);
        throw new InvalidPicSortException("invalid sort parameter. Sort should be 'new' or 'popular'");
    }

    public Page<Pic> getPicsByUser(User user, int page) {
        Pageable pageable = PageRequest.of(page - 1, picOnPage);
        return picRepository.findByUserOwnerAndStatus(user, 1, pageable);
    }

    public Optional<Pic> getMyPic(long picId, Authentication authentication) {
        if (authentication == null) return Optional.empty();
        PicnetUserPrincipal principal = (PicnetUserPrincipal) authentication.getPrincipal();
        return picRepository.findByIdAndUserOwner(picId, principal.getUser());
    }

    public Page<Pic> getOwnPics(User user, int page) {
        Pageable pageable = PageRequest.of(page - 1, picOnPage);
        return picRepository.findByUserOwner(user, pageable);
    }

    public Optional<Pic> getPicForModer() {
        return picRepository.getFirstByStatus(0);
    }

    public void moderPic(long picId, String isOk) {
        Pic pic = picRepository.getOne(picId);
        switch (isOk){
            case "ok":
                pic.setStatus(1);
                break;
            case "no":
                pic.setStatus(2);
                break;
        }
        picRepository.save(pic);
    }
}
