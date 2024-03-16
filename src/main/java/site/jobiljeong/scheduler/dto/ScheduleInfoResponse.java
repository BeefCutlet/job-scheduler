package site.jobiljeong.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfoResponse {

    private ScheduleType scheduleType;
    private LocalDateTime scheduleDate;
    private String memo;
    private String websiteAddress;
    private String scheduleGroup;
    private String companyName;

    public ScheduleInfoResponse createScheduleInfo(Schedule schedule) {
        this.scheduleType = schedule.getScheduleType();
        this.scheduleDate = schedule.getScheduleDate();
        this.memo = schedule.getMemo();
        this.websiteAddress = schedule.getWebsiteAddress();
        this.scheduleGroup = schedule.getScheduleGroup();
        this.companyName = schedule.getCompany().getName();
        return this;
    }
}
