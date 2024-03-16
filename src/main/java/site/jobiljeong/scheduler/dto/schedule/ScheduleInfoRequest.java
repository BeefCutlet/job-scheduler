package site.jobiljeong.scheduler.dto.schedule;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleInfoRequest {

    private LocalDate requestDate;
}
