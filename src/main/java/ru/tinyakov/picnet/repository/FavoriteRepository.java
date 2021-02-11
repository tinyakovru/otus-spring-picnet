package ru.tinyakov.picnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinyakov.picnet.domain.Favorite;
import ru.tinyakov.picnet.domain.FavoriteKey;
import ru.tinyakov.picnet.domain.Tag;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteKey> {

}
