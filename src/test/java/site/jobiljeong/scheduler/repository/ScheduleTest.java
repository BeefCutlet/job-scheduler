package site.jobiljeong.scheduler.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;
import site.jobiljeong.scheduler.repository.schedule.ScheduleRepository;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class ScheduleTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    void insertScheduleSuccess() {
        Schedule schedule = createSchedule();

        Schedule savedSchedule = scheduleRepository.save(schedule);

        assertThat(savedSchedule.getScheduleType()).isEqualTo(ScheduleType.MEETING);
        assertThat(savedSchedule.getScheduleGroup()).isEqualTo("ScheduleGroup");
        assertThat(savedSchedule.getMemo()).isEqualTo("ScheduleMemo");
        assertThat(savedSchedule.getWebsiteAddress()).isEqualTo("ScheduleWebsiteAddress");
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
