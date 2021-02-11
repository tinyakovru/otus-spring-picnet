package ru.tinyakov.picnet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinyakov.picnet.domain.Pic;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.repository.PicRepository;
import ru.tinyakov.picnet.repository.UserRepository;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class PicService {
    private final PicRepository picRepository;
    private final UserRepository userRepository;

//    public void addFavorite(long pid, String nickname){
//        Pic pic = picRepository.getOne(pid);
//        User user = userRepository.findByNickname(nickname);
//        pic.getUsersFavorite().addAll(Arrays.asList(user));
//        picRepository.save(pic);
////        pic.getUsersFavorite().add(user);
////        picRepository.save(pic);
//    }

    public Pic getPic(long id) {
        return picRepository.getOne(id);
    }
}
