package ru.tinyakov.picnet.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinyakov.picnet.domain.Comment;
import ru.tinyakov.picnet.domain.Pic;
import ru.tinyakov.picnet.domain.Tag;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class PicPageDto {
    private Pic pic;
    private boolean isLiked;
    private int likeCount;
    private long userId;
    private String nickname;
//    private List<Comment> comments;
    private boolean isAuth;
}
