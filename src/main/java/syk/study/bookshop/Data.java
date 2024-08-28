package syk.study.bookshop;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import syk.study.bookshop.entity.Book;
import syk.study.bookshop.entity.User;
import syk.study.bookshop.repository.BookRepository;
import syk.study.bookshop.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class Data {
    private final UserDataInit userDataInit;

    private String client_id;
    private String client_secret;

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
