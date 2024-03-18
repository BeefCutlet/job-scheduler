package site.jobiljeong.scheduler.dto.schedule;

import lombok.Getter;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;

import java.time.LocalDateTime;

@Getter
public class ScheduleUpdateRequest {

    private Long scheduleNo;
    private ScheduleType scheduleType;
    private LocalDateTime scheduleDate;
    private String memo;
    private String websiteAddress;
}
