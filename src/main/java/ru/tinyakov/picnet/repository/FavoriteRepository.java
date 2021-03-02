package ru.tinyakov.picnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tinyakov.picnet.domain.*;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteKey> {
    int countByPicId(long picId);
//    Favorite findByUserAndPic(User user, Pic pic);

//    @Query("delete from Favorite f where f.user.id = :userId and f.pic.id=:picId")
//    void deleteFavorite(long userId, long picId);
}
