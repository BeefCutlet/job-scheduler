package site.jobiljeong.scheduler.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.jobiljeong.scheduler.dto.UserInfo;
import site.jobiljeong.scheduler.dto.UserSaveRequest;
import site.jobiljeong.scheduler.entity.custom.UserStatus;
import site.jobiljeong.scheduler.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    /**
     * 유저 정보 저장 (회원가입)
     * @param userSaveRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<?> saveUserInfo(UserSaveRequest userSaveRequest) {
        userService.saveUserInfo(userSaveRequest);
        return ResponseEntity.noContent().build();
    }

    /**
     * 회원 탈퇴
     * @param session
     * @return
     */
    @PutMapping("/withdrawal")
    public ResponseEntity<?> updateUserStatus(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        userService.changeUserStatus(userId, UserStatus.WITHDRAW);
        return ResponseEntity.noContent().build();
    }

    /**
     * 현재 접속 중인 유저 정보 단건 조회
     * @param session
     * @return
     */
    @GetMapping("/info")
    public ResponseEntity<UserInfo> findUserInfo(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        UserInfo userInfo = userService.findUserInfo(userId);
        return ResponseEntity.ok(userInfo);
    }
}
