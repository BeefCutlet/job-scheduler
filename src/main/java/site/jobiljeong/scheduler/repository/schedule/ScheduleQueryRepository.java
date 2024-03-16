package site.jobiljeong.scheduler.repository.schedule;

import site.jobiljeong.scheduler.dto.schedule.ScheduleInfoResponse;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleQueryRepository {

    List<ScheduleInfoResponse> findScheduleList(Long userId, LocalDate currentTime);
}
