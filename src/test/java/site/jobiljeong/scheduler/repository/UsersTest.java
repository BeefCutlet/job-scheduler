package site.jobiljeong.scheduler.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.jobiljeong.scheduler.entity.Users;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class UsersTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void insertUsersSuccess() {
        Users user = createUsers();

        usersRepository.save(user);

        Users savedUser = usersRepository.findById(user.getId()).get();

        assertThat(savedUser.getUid()).isEqualTo("UserUid");
        assertThat(savedUser.getPassword()).isEqualTo("UserPassword");
        assertThat(savedUser.getNickname()).isEqualTo("UserNickname");
        assertThat(savedUser.getAuthority()).isEqualTo("ROLE_USER");
    }

    private Users createUsers() {
        return Users.builder()
                .uid("UserUid")
                .password("UserPassword")
                .nickname("UserNickname")
                .authority("ROLE_USER")
                .build();
    }
}
