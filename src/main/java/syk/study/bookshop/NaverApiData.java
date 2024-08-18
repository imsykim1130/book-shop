package syk.study.bookshop;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import syk.study.bookshop.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class NaverApiData {

    private final BookRepository repository;

    private String client_id;
    private String client_secret;

    @PostConstruct
    public void init() {

    }
}
