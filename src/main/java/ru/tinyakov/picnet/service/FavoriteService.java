package ru.tinyakov.picnet.service;

import ru.tinyakov.picnet.domain.Favorite;

public interface FavoriteService {
    Favorite create(String nickname, long picId);
    void delete(Favorite favorite);
}
