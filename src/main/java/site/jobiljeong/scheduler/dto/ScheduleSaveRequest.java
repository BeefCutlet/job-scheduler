package site.jobiljeong.scheduler.dto;

import lombok.Getter;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.Users;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveRequest {

    private ScheduleType scheduleType;
    private LocalDateTime scheduleDate;
    private String memo;
    private String websiteAddress;
    private String scheduleGroup;
    private String companyName;

    public Schedule createSchedule(Users users, Company company) {
        return Schedule.builder()
                .scheduleType(scheduleType)
                .scheduleDate(scheduleDate)
                .memo(memo)
                .websiteAddress(websiteAddress)
                .scheduleGroup(scheduleGroup)
                .users(users)
                .company(company)
                .build();
    }

    public Company createCompany() {
        return Company.builder()
                .name(companyName)
                .build();
    }

}
