package site.jobiljeong.scheduler.dto.user;

import lombok.Builder;
import lombok.Getter;
import site.jobiljeong.scheduler.entity.Users;

@Getter
@Builder
public class UserReadResponse {

    private String uid;
    private String nickname;
    private String authority;

    public static UserReadResponse of(Users user) {
        return UserReadResponse.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .authority(user.getAuthority())
                .build();
    }
}
