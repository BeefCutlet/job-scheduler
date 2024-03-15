package site.jobiljeong.scheduler.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.jobiljeong.scheduler.dto.UserInfo;
import site.jobiljeong.scheduler.dto.UserSaveRequest;
import site.jobiljeong.scheduler.entity.Users;
import site.jobiljeong.scheduler.entity.custom.UserStatus;
import site.jobiljeong.scheduler.repository.users.UsersRepository;
import site.jobiljeong.scheduler.service.users.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("회원 정보 저장 성공")
    void saveUserInfoSuccess() {
        //given
        UserSaveRequest userSaveRequest = new UserSaveRequest("UserUid", "UserPassword", "UserNickname");

        //when
        userService.saveUserInfo(userSaveRequest);

        //then
        verify(usersRepository).save(any());
    }

    @Test
    @DisplayName("회원 상태 변경 성공")
    void changeUserStatusSuccess() {
        //given
        Long requestId = 1L;
        Users user = Users.builder()
                .id(1L)
                .uid("UserUid")
                .password("UserPassword")
                .nickname("UserNickname")
                .status(UserStatus.ACTIVE)
                .authority("ROLE_USER")
                .build();

        //when
        when(usersRepository.findById(requestId)).thenReturn(Optional.ofNullable(user));

        userService.changeUserStatus(requestId, UserStatus.WITHDRAW);

        //then
        assertThat(user.getStatus()).isEqualTo(UserStatus.WITHDRAW);
    }

    @Test
    @DisplayName("회원 상태 변경 시에 회원 정보 조회 실패")
    void findUserInfoFailedWhenChangeUserStatus() {
        //given
        Long requestId = 1L;

        //when
        when(usersRepository.findById(requestId)).thenReturn(Optional.ofNullable(null));

        //then
        assertThatThrownBy(() -> userService.changeUserStatus(requestId, UserStatus.WITHDRAW))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("회원 정보 단건 조회 성공")
    void findUserInfoSuccess() {
        //given
        Long requestId = 1L;
        Users user = Users.builder()
                .id(1L)
                .uid("UserUid")
                .password("UserPassword")
                .nickname("UserNickname")
                .status(UserStatus.ACTIVE)
                .authority("ROLE_USER")
                .build();

        //when
        when(usersRepository.findById(requestId)).thenReturn(Optional.ofNullable(user));

        UserInfo userInfo = userService.findUserInfo(requestId);

        //then
        assertThat(userInfo.getUid()).isEqualTo("UserUid");
        assertThat(userInfo.getNickname()).isEqualTo("UserNickname");
        assertThat(userInfo.getAuthority()).isEqualTo("ROLE_USER");
    }

    @Test
    @DisplayName("회원 정보 단건 조회 실패")
    void findUserInfoFailed() {
        //given
        Long requestId = 1L;

        //when
        when(usersRepository.findById(requestId)).thenReturn(Optional.ofNullable(null));

        //then
        assertThatThrownBy(() -> userService.changeUserStatus(requestId, UserStatus.WITHDRAW))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
