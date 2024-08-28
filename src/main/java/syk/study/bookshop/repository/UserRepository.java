package syk.study.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syk.study.bookshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
