package ru.tinyakov.picnet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinyakov.picnet.domain.Favorite;
import ru.tinyakov.picnet.domain.FavoriteKey;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.repository.FavoriteRepository;
import ru.tinyakov.picnet.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;

    @Override
    public Favorite create(String nickname, long picId) {
        User user = userRepository.findByNickname(nickname);

        Favorite favorite = new Favorite(new FavoriteKey(user.getId(), picId));
        return favoriteRepository.save(favorite);
    }

    @Override
    public void delete(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }
}
