package site.jobiljeong.scheduler.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleInfoRequest {

    private LocalDateTime scheduleRangeStart;
    private LocalDateTime scheduleRangeEnd;
}
