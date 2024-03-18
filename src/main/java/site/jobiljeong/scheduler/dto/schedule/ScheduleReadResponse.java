package site.jobiljeong.scheduler.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleReadResponse {

    private ScheduleType scheduleType;
    private LocalDateTime scheduleDate;
    private String memo;
    private String websiteAddress;
    private String scheduleGroup;
    private String companyName;

    public ScheduleReadResponse createSchedule(Schedule schedule) {
        this.scheduleType = schedule.getScheduleType();
        this.scheduleDate = schedule.getScheduleDate();
        this.memo = schedule.getMemo();
        this.websiteAddress = schedule.getWebsiteAddress();
        this.scheduleGroup = schedule.getScheduleGroup();
        this.companyName = schedule.getCompany().getName();
        return this;
    }
}
