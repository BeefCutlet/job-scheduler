package site.jobiljeong.scheduler.service.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.jobiljeong.scheduler.dto.UserInfo;
import site.jobiljeong.scheduler.dto.UserSaveRequest;
import site.jobiljeong.scheduler.entity.Users;
import site.jobiljeong.scheduler.entity.custom.UserStatus;
import site.jobiljeong.scheduler.repository.users.UsersRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UsersRepository usersRepository;

    /**
     * 유저 정보를 DB에 저장
     * @param userSaveRequest
     */
    public void saveUserInfo(UserSaveRequest userSaveRequest) {
        usersRepository.save(userSaveRequest.convertToEntity());
    }

    /**
     * 유저 상태(활성, 휴면, 탈퇴) 변경
     * @param userId
     */
    public void changeUserStatus(Long userId, UserStatus status) {
        Users user = usersRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));
        user.changeUserStatus(status);
    }

    /**
     * 유저 정보 단건 조회
     * @param userId
     * @return
     */
    public UserInfo findUserInfo(Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));
        return new UserInfo().createUserInfo(user);
    }
}
