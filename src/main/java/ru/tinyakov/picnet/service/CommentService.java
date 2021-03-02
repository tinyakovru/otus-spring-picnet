package ru.tinyakov.picnet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinyakov.picnet.domain.Comment;
import ru.tinyakov.picnet.domain.FavoriteKey;
import ru.tinyakov.picnet.domain.Pic;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.repository.CommentRepository;
import ru.tinyakov.picnet.repository.PicRepository;
import ru.tinyakov.picnet.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PicRepository picRepository;

    public void createComment(String commentText, User user, long picId){
        Comment comment = new Comment();
        comment.setText(commentText);
        comment.setPic(picRepository.getOne(picId));
        comment.setUser(user);
        commentRepository.save(comment);
    }

    public void deleteComment(String nickname, long picId) {
        log.info("delete comment nickname:{} picID:{}",nickname,picId);
        User user = userRepository.findByNickname(nickname);
        Pic pic = picRepository.getOne(picId);
//        log.info("user={}",user.toString());
//        log.info("pic={}",pic.toString());
        Comment comment = commentRepository.findByUserAndPic(user, pic);
//        log.info("comment={}",comment.toString());
        commentRepository.delete(comment);
    }
}
