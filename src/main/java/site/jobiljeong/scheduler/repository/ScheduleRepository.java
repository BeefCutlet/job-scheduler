package site.jobiljeong.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.jobiljeong.scheduler.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
