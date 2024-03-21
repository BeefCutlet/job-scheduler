package site.jobiljeong.scheduler.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import site.jobiljeong.scheduler.dto.attachment.AttachmentResponse;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ScheduleReadResponse {

    private Long scheduleNo;
    private ScheduleType scheduleType;
    private LocalDateTime scheduleDate;
    private String memo;
    private String websiteAddress;
    private String scheduleGroup;
    private String companyName;
    private List<AttachmentResponse> attachmentList;

    public static ScheduleReadResponse of(Schedule schedule, List<AttachmentResponse> attachmentList) {
        return ScheduleReadResponse.builder()
                .scheduleNo(schedule.getId())
                .scheduleType(schedule.getScheduleType())
                .scheduleDate(schedule.getScheduleDate())
                .memo(schedule.getMemo())
                .websiteAddress(schedule.getWebsiteAddress())
                .scheduleGroup(schedule.getScheduleGroup())
                .companyName(schedule.getCompany().getName())
                .attachmentList(attachmentList)
                .build();
    }
}
