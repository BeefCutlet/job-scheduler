package site.jobiljeong.scheduler.dto.user;

import lombok.Getter;
import site.jobiljeong.scheduler.entity.Users;

@Getter
public class UserReadResponse {

    private String uid;
    private String nickname;
    private String authority;

    public UserReadResponse createUser(Users user) {
        this.uid = user.getUid();
        this.nickname = user.getNickname();
        this.authority = user.getAuthority();
        return this;
    }
}
