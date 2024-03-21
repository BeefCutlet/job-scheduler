package site.jobiljeong.scheduler.service.attachment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.jobiljeong.scheduler.dto.FileInfo;
import site.jobiljeong.scheduler.dto.attachment.AttachmentSaveRequest;
import site.jobiljeong.scheduler.entity.Attachment;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.repository.attachment.AttachmentRepository;
import site.jobiljeong.scheduler.repository.schedule.ScheduleRepository;
import site.jobiljeong.scheduler.util.FileService;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentService {

    private final ScheduleRepository scheduleRepository;
    private final AttachmentRepository attachmentRepository;
    private final FileService fileService;

    /**
     * 첨부파일 정보를 DB에 저장
     */
    public void saveAttachment(AttachmentSaveRequest attachmentRequest, MultipartFile file) {
        Schedule schedule = scheduleRepository.findById(attachmentRequest.getScheduleNo()).orElseThrow(
                () -> new IllegalArgumentException("일정 정보를 찾을 수 없습니다."));
        FileInfo fileInfo = fileService.storeFile(file);
        Attachment attachment = attachmentRequest.createAttachment(schedule, fileInfo);
        attachmentRepository.save(attachment);
    }

    /**
     * 스토리지에서 파일 삭제 후 성공하면 DB에서 파일 정보 삭제
     */
    public void deleteAttachment(Long attachmentNo) {
        Attachment attachment = attachmentRepository.findById(attachmentNo).orElseThrow(() ->
            new IllegalArgumentException("첨부 파일 정보가 존재하지 않습니다."));
        attachmentRepository.deleteById(attachmentNo);
        fileService.deleteFile(attachment.getUrl());
    }
}
