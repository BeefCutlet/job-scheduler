package site.jobiljeong.scheduler.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.jobiljeong.scheduler.entity.Attachment;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;
import site.jobiljeong.scheduler.repository.attachment.AttachmentRepository;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class AttachmentTest {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Test
    void insertAttachmentSuccess() {
        Attachment attachment = createAttachment();

        Attachment savedAttachment = attachmentRepository.save(attachment);

        assertThat(savedAttachment.getName()).isEqualTo("AttachmentName");
        assertThat(savedAttachment.getCategory()).isEqualTo("AttachmentCategory");
        assertThat(savedAttachment.getAddress()).isEqualTo("AttachmentAddress");
    }

    private Attachment createAttachment() {
        Schedule schedule = createSchedule();
        return Attachment.builder()
                .name("AttachmentName")
                .category("AttachmentCategory")
                .address("AttachmentAddress")
                .schedule(schedule)
                .build();
    }

    private Schedule createSchedule() {
        Company company = createCompany();
        return Schedule.builder()
                .scheduleType(ScheduleType.MEETING)
                .scheduleGroup("ScheduleGroup")
                .websiteAddress("ScheduleWebsiteAddress")
                .memo("ScheduleMemo")
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
}
