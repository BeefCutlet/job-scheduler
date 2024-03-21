package site.jobiljeong.scheduler.dto.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.jobiljeong.scheduler.entity.Attachment;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.custom.AttachmentCategory;

@Getter
@AllArgsConstructor
public class AttachmentSaveRequest {

    private Long scheduleNo;
    private String name;
    private AttachmentCategory category;
    private String url;

    public Attachment createAttachment(Schedule schedule) {
        return Attachment.builder()
                .originName(name)
                .savedName(Attachment.createSavedName(name))
                .category(category)
                .url(url)
                .schedule(schedule)
                .build();
    }
}
