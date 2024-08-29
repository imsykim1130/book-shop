package syk.study.bookshop.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @Embedded
    @NonNull
    private Address address; // street, city
    private String authority; // 권한(user, admin)
    private boolean isValid; // 탈퇴 or 휴면 여부

    @Builder
    public User(String username, String password, String street, String city) {
        this.username = username;
        this.password = password;
        this.address = new Address(street, city);
        this.authority = "user";
        this.isValid = true;
    }
}
