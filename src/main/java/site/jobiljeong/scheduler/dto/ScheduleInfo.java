package site.jobiljeong.scheduler.dto;

import lombok.Getter;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;

import java.time.LocalDateTime;

@Getter
public class ScheduleInfo {

    private ScheduleType scheduleType;
    private LocalDateTime scheduleDate;
    private String memo;
    private String websiteAddress;
    private String scheduleGroup;
    private String companyName;

    public ScheduleInfo createScheduleInfo(Schedule schedule) {
        this.scheduleType = schedule.getScheduleType();
        this.scheduleDate = schedule.getScheduleDate();
        this.memo = schedule.getMemo();
        this.websiteAddress = schedule.getWebsiteAddress();
        this.scheduleGroup = schedule.getScheduleGroup();
        this.companyName = schedule.getCompany().getName();
        return this;
    }
}
