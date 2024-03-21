package site.jobiljeong.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.jobiljeong.scheduler.dto.attachment.AttachmentSaveRequest;
import site.jobiljeong.scheduler.service.attachment.AttachmentService;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    /**
     * 첨부파일 정보를 DB에 저장
     */
    @PostMapping
    public ResponseEntity<?> saveAttachment(@RequestPart AttachmentSaveRequest attachmentRequest,
                                            @RequestPart MultipartFile file) {
        attachmentService.saveAttachment(attachmentRequest, file);
        return ResponseEntity.noContent().build();
    }

    /**
     * DB에서 첨부 파일 정보 삭제 및 스토리지에서 파일 삭제
     */
    @DeleteMapping("/{attachmentNo}")
    public ResponseEntity<?> deleteAttachment(@PathVariable Long attachmentNo) {
        attachmentService.deleteAttachment(attachmentNo);
        return ResponseEntity.noContent().build();
    }
}
