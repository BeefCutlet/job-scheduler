package site.jobiljeong.scheduler.dto.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.jobiljeong.scheduler.dto.FileInfo;
import site.jobiljeong.scheduler.entity.Attachment;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.custom.AttachmentCategory;

@Getter
@AllArgsConstructor
public class AttachmentSaveRequest {

    private Long scheduleNo;
    private AttachmentCategory category;

    public Attachment createAttachment(Schedule schedule, FileInfo fileInfo) {
        return Attachment.builder()
                .originName(fileInfo.getOriginFilename())
                .savedName(fileInfo.getSavedFilename())
                .category(category)
                .url(fileInfo.getFilePath())
                .schedule(schedule)
                .build();
    }
}
