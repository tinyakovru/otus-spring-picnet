package ru.tinyakov.picnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinyakov.picnet.domain.Comment;
import ru.tinyakov.picnet.domain.Pic;
import ru.tinyakov.picnet.domain.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment findByUserAndPic(User user, Pic pic);
    List<Comment> findByPicAndStatus(Pic pic, int Status);
}
