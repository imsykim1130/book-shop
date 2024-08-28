package syk.study.bookshop;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import syk.study.bookshop.entity.User;
import syk.study.bookshop.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class Data {
    private final UserDataInit userDataInit;

    @PostConstruct
    public void init() {
        userDataInit.init1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class UserDataInit
    {
        private final UserRepository userRepository;

        public void init1() {
            User user = userRepository.save(new User("admin", "admin"));
        }
    }
}
