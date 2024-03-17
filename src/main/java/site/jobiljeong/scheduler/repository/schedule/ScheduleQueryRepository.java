package site.jobiljeong.scheduler.repository.schedule;

import site.jobiljeong.scheduler.dto.schedule.ScheduleReadResponse;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleQueryRepository {

    List<ScheduleReadResponse> findScheduleList(Long userId, LocalDate currentTime);
}
