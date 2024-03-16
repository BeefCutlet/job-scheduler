package site.jobiljeong.scheduler.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleInfoRequest {

    private LocalDate requestDate;
}
