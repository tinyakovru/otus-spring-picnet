package ru.tinyakov.picnet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.tinyakov.picnet.domain.Pic;
import ru.tinyakov.picnet.domain.Tag;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.repository.PicRepository;
import ru.tinyakov.picnet.repository.TagRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {

    private final TagRepository tagRepository;
    private final PicRepository picRepository;

    public Set<Tag> updateTags(Pic pic, User user, String tagString) {
        log.info("updateTags");
        Set<String> tagStringSet = sliceTags(tagString);
        Set<Tag> tags = createTagsIfNotExist(tagStringSet, user);
//        removeTagPic(pic);
        updateTagPic(pic, tags);
        return pic.getTags();
    }

    private Set<String> sliceTags(String tagString) {
        log.info("tagString before replace={}", tagString);
        tagString = tagString.replaceAll("[^A-Za-zА-Яа-я0-9 ]", "");
        log.info("tagString after replace={}", tagString);
        tagString = tagString.trim().replaceAll(" +", " ");
        String[] tagsArr = tagString.split(" ");
        log.info("tag array={}", Arrays.toString(tagsArr));
        return new HashSet<>(Arrays.asList(tagsArr));
    }

    private Set<Tag> createTagsIfNotExist(Set<String> tagStringSet, User user) {
        Set<Tag> tags = new HashSet<>();
        for (String tagString : tagStringSet) {
            tagRepository.findByName(tagString)
                    .or(() -> {
                        val t = new Tag();
                        t.setUser(user);
                        t.setName(tagString);
                        tagRepository.save(t);
                        return  Optional.of(t);
                    })
                    .map((tag) -> tags.add(tag));
        }
        log.info("create tags if not exist");
        return tags;
    }

    private void updateTagPic(Pic pic, Set<Tag> tags) {
        pic.setTags(tags);
        picRepository.save(pic);
    }

    public Tag getTag(long tagId) {
        return tagRepository.getOne(tagId);
    }
}
