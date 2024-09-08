package syk.study.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syk.study.bookshop.entity.User;
import syk.study.bookshop.repository.UserRepository;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

}
