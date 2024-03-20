package site.jobiljeong.scheduler.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import site.jobiljeong.scheduler.dto.attachment.AttachmentSaveRequest;
import site.jobiljeong.scheduler.entity.Attachment;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.custom.AttachmentCategory;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;
import site.jobiljeong.scheduler.repository.attachment.AttachmentRepository;
import site.jobiljeong.scheduler.repository.schedule.ScheduleRepository;
import site.jobiljeong.scheduler.service.attachment.AttachmentService;
import site.jobiljeong.scheduler.util.FileService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttachmentServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private AttachmentRepository attachmentRepository;
    @Mock
    private FileService fileService;

    @InjectMocks
    private AttachmentService attachmentService;

    @Test
    void saveAttachmentSuccess() {
        //given
        Long scheduleNo = 1L;
        Schedule schedule = createSchedule();

        //mocking
        ReflectionTestUtils.setField(schedule, "id", scheduleNo);
        when(scheduleRepository.findById(scheduleNo)).thenReturn(Optional.of(schedule));

        //when
        attachmentService.saveAttachment(scheduleNo, createAttachmentRequestList());

        //then
        verify(attachmentRepository).save(any());
    }

    @Test
    void storeAttachmentFileSuccess() {

    }

    private List<AttachmentSaveRequest> createAttachmentRequestList() {
        AttachmentSaveRequest attachmentRequest1 = new AttachmentSaveRequest(
                1L,
                "AttachmentName",
                AttachmentCategory.RESUME,
                "AttachmentUrl");
        List<AttachmentSaveRequest> attachmentRequestList = new ArrayList<>();
        attachmentRequestList.add(attachmentRequest1);

        return attachmentRequestList;
    }

    private Schedule createSchedule() {
        Company company = createCompany();
        return Schedule.builder()
                .scheduleType(ScheduleType.MEETING)
                .scheduleGroup("ScheduleGroup")
                .websiteAddress("ScheduleWebsiteAddress")
                .memo("ScheduleMemo")
                .scheduleDate(LocalDateTime.of(2024, 3, 15, 1, 1))
                .company(company)
                .build();
    }

    private Company createCompany() {
        return Company.builder()
                .name("CompanyName")
                .websiteAddress("CompanyWebsiteAddress")
                .memo("CompanyMemo")
                .build();
    }

    private Attachment createAttachment() {
        return Attachment.builder()
                .name("AttachmentName")
                .category(AttachmentCategory.RESUME)
                .url("AttachmentUrl")
                .build();
    }
}
