package syk.study.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syk.study.bookshop.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
