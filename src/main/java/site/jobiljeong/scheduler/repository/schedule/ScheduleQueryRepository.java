package site.jobiljeong.scheduler.repository.schedule;

import site.jobiljeong.scheduler.dto.ScheduleInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleQueryRepository {

    List<ScheduleInfo> findScheduleList(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}
