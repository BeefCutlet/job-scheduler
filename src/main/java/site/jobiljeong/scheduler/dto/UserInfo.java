package site.jobiljeong.scheduler.dto;

import lombok.Getter;
import site.jobiljeong.scheduler.entity.Users;

@Getter
public class UserInfo {

    private String uid;
    private String nickname;
    private String authority;

    public UserInfo createUserInfo(Users user) {
        this.uid = user.getUid();
        this.nickname = user.getNickname();
        this.authority = user.getAuthority();
        return this;
    }
}
