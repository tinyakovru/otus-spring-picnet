package ru.tinyakov.picnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinyakov.picnet.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByNickname(String nickname);
    //User findByEmail(String email);
}
