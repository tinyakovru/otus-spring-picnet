package ru.tinyakov.picnet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tinyakov.picnet.config.PicnetUserPrincipal;
import ru.tinyakov.picnet.domain.User;
import ru.tinyakov.picnet.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class PicnetUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(nickname);
        if(user == null) throw new UsernameNotFoundException(nickname);
        return new PicnetUserPrincipal(user);
    }
}
