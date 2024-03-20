package site.jobiljeong.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.jobiljeong.scheduler.dto.attachment.AttachmentSaveRequest;
import site.jobiljeong.scheduler.service.attachment.AttachmentService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    /**
     * 첨부파일 정보를 DB에 저장
     */
    @PostMapping("/{scheduleNo}")
    public ResponseEntity<?> saveAttachment(@PathVariable Long scheduleNo,
                                            @RequestBody List<AttachmentSaveRequest> attachmentSaveRequest) {
        attachmentService.saveAttachment(scheduleNo, attachmentSaveRequest);
        return ResponseEntity.noContent().build();
    }

    /**
     * 첨부파일을 스토리지에 저장
     */
    @PostMapping(name = "/store", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> storeAttachmentFile(@RequestBody MultipartFile file) {
        //생성된 파일 URL 반환
        String url = attachmentService.storeAttachmentFile(file);
        return ResponseEntity.created(URI.create(url)).build();
    }

    /**
     * DB에서 첨부 파일 정보 삭제 및 스토리지에서 파일 삭제
     */
    @DeleteMapping("/{attachmentNo}")
    public ResponseEntity<?> deleteAttachment(@PathVariable Long attachmentNo) {
        attachmentService.deleteAttachment(attachmentNo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAttachmentFiles() {
        return ResponseEntity.ok().build();
    }
}
