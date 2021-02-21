package ru.tinyakov.picnet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tinyakov.picnet.domain.Pic;
import ru.tinyakov.picnet.domain.Tag;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.domain.dto.PicInfoDto;
import ru.tinyakov.picnet.exception.PicNotFoundException;
import ru.tinyakov.picnet.repository.PicRepository;
import ru.tinyakov.picnet.repository.TagRepository;
import ru.tinyakov.picnet.repository.UserRepository;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class PicService {
    private final PicRepository picRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final TagService tagService;


    public Pic getPic(long id) {
        return picRepository.getOne(id);
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
        Pageable pageable = PageRequest.of(page-1, 2);
        Tag tag = tagRepository.getOne(tagId);
        return picRepository.findByTagsContaining(tag,pageable);

    }
}
