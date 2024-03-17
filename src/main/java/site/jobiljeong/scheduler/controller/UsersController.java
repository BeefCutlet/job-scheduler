package site.jobiljeong.scheduler.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.jobiljeong.scheduler.dto.user.UserReadResponse;
import site.jobiljeong.scheduler.dto.user.UserSaveRequest;
import site.jobiljeong.scheduler.entity.custom.UserStatus;
import site.jobiljeong.scheduler.service.users.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    /**
     * 유저 정보 저장 (회원가입)
     */
    @PostMapping
    public ResponseEntity<?> saveUserInfo(@RequestBody UserSaveRequest userSaveRequest) {
        userService.saveUserInfo(userSaveRequest);
        return ResponseEntity.noContent().build();
    }

    /**
     * 회원 탈퇴
     */
    @PutMapping("/withdrawal")
    public ResponseEntity<?> updateUserStatus(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        userService.changeUserStatus(userId, UserStatus.WITHDRAW);
        return ResponseEntity.noContent().build();
    }

    /**
     * 현재 접속 중인 유저 정보 단건 조회
     */
    @GetMapping("/info")
    public ResponseEntity<UserReadResponse> findUserInfo(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        UserReadResponse userReadResponse = userService.findUserInfo(userId);
        return ResponseEntity.ok(userReadResponse);
    }
}
