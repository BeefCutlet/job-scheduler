package site.jobiljeong.scheduler.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.jobiljeong.scheduler.entity.Users;
import site.jobiljeong.scheduler.entity.custom.UserStatus;

@Getter
@AllArgsConstructor
public class UserSaveRequest {

    private String uid;
    private String password;
    private String nickname;

    public Users convertToEntity() {
        return Users.builder()
                .uid(this.uid)
                .password(this.password)
                .nickname(this.nickname)
                .authority("ROLE_USER")
                .status(UserStatus.ACTIVE)
                .build();
    }
}
