package site.jobiljeong.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.jobiljeong.scheduler.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
