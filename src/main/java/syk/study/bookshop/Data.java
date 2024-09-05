package syk.study.bookshop;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import syk.study.bookshop.entity.Address;
import syk.study.bookshop.entity.User;
import syk.study.bookshop.repository.AddressRepository;
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
        private final AddressRepository addressRepository;

        public void init1() {
            Address address1 = new Address("street", "city");
            addressRepository.save(address1);
            User user1 = new User("admin", "admin", address1);
            userRepository.save(user1);

            Address address2 = new Address("street", "city");
            addressRepository.save(address2);
            User user2 = new User("admin2", "admin2", address2);
            userRepository.save(user2);

        }
    }
}
