package ru.tinyakov.picnet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import ru.tinyakov.picnet.domain.Pic;
import ru.tinyakov.picnet.domain.Tag;
import ru.tinyakov.picnet.domain.User;

import java.util.Optional;

public interface PicRepository extends JpaRepository<Pic,Long> {

    @PostAuthorize("returnObject.status == 1 or hasRole('ADMIN') or authentication.name == returnObject.userOwner.nickname")
    Pic getOne(long id);

    Page<Pic> findByTagsContainingAndStatus(Tag tag, int status, Pageable pageable);

    Page<Pic> findByStatusOrderByIdDesc(int status, Pageable pageable);

    Page<Pic> findByStatusOrderByRatingDesc(int status, Pageable pageable);

    Page<Pic> findByUserOwnerAndStatus(User user, int status, Pageable pageable);

    Page<Pic> findByUserOwner(User user, Pageable pageable);

    Optional<Pic> findByIdAndUserOwner(long id, User user);

    Optional<Pic> getFirstByStatus(int status);
}
