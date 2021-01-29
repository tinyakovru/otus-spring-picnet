package ru.tinyakov.picnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinyakov.picnet.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
