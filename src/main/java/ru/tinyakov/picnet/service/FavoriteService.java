package ru.tinyakov.picnet.service;

import ru.tinyakov.picnet.domain.Favorite;

import java.security.Principal;
import java.util.Optional;

public interface FavoriteService {
    Favorite create(String nickname, long picId);
    Optional<Favorite> get(long userId, long picId);
    int getCountByPicId(long picId);
    void delete(long userId, long picId);
}
