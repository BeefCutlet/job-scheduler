package site.jobiljeong.scheduler.dto.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttachmentFileResponse {
    private String url;

    public static AttachmentFileResponse of(String url) {
        return new AttachmentFileResponse(url);
    }
}
