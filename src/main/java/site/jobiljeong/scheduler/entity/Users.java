package site.jobiljeong.scheduler.entity;

import jakarta.persistence.*;
import lombok.*;
import site.jobiljeong.scheduler.entity.custom.UserStatus;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    private String uid;

    private String password;

    private String nickname;

    private String authority;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public void changeUserStatus(UserStatus status) {
        this.status = status;
    }
}
