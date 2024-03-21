package site.jobiljeong.scheduler.dto.attachment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import site.jobiljeong.scheduler.entity.Attachment;
import site.jobiljeong.scheduler.entity.custom.AttachmentCategory;

@Getter
@AllArgsConstructor
@Builder
public class AttachmentResponse {

    private String attachmentName;
    private AttachmentCategory attachmentCategory;
    private String attachmentUrl;

    public static AttachmentResponse of(Attachment attachment) {
        return AttachmentResponse.builder()
                .attachmentName(attachment.getOriginName())
                .attachmentCategory(attachment.getCategory())
                .attachmentUrl(attachment.getUrl())
                .build();
    }
}
