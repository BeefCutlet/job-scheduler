package site.jobiljeong.scheduler.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.jobiljeong.scheduler.dto.schedule.ScheduleReadResponse;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.Users;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;
import site.jobiljeong.scheduler.entity.custom.UserStatus;
import site.jobiljeong.scheduler.repository.company.CompanyRepository;
import site.jobiljeong.scheduler.repository.schedule.ScheduleQueryRepository;
import site.jobiljeong.scheduler.repository.schedule.ScheduleRepository;
import site.jobiljeong.scheduler.repository.users.UsersRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@DataJpaTest
public class ScheduleTest {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleQueryRepository scheduleQueryRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Test
    void insertScheduleSuccess() {
        Schedule schedule = createSchedule();

        assertThat(schedule.getScheduleType()).isEqualTo(ScheduleType.MEETING);
        assertThat(schedule.getScheduleGroup()).isEqualTo("ScheduleGroup");
        assertThat(schedule.getMemo()).isEqualTo("ScheduleMemo");
        assertThat(schedule.getWebsiteAddress()).isEqualTo("ScheduleWebsiteAddress");
    }

    @Test
    void findScheduleWithinPeriodSuccess() {
        Schedule schedule = createSchedule();
        Schedule scheduleAnotherDay = createScheduleAnotherDay();

        List<ScheduleReadResponse> scheduleList = scheduleQueryRepository.findScheduleList(
                schedule.getUsers().getId(),
                LocalDate.of(2024, 3, 11));

        log.info("ScheduleDate == {}", scheduleList.get(0).getScheduleDate().toString());
        assertThat(scheduleList.get(0).getScheduleDate()).isEqualTo(LocalDateTime.of(2024, 3, 15, 1, 1));
//        assertThat(scheduleList.get(0).getScheduleDate()).isEqualTo(LocalDateTime.of(2024, 2, 15, 1, 1));
        assertThat(scheduleList.get(0).getCompanyName()).isEqualTo("CompanyName");
    }

    private Schedule createSchedule() {
        Company company = createCompany();
        Users user = createUsers();

        Schedule schedule = Schedule.builder()
                .scheduleType(ScheduleType.MEETING)
                .scheduleGroup("ScheduleGroup")
                .websiteAddress("ScheduleWebsiteAddress")
                .memo("ScheduleMemo")
                .scheduleDate(LocalDateTime.of(2024, 3, 15, 1, 1))
                .users(user)
                .company(company)
                .build();

        return scheduleRepository.save(schedule);
    }

    private Schedule createScheduleAnotherDay() {
        Company company = createCompany();
        Users user = createUsers();

        Schedule schedule = Schedule.builder()
                .scheduleType(ScheduleType.MEETING)
                .scheduleGroup("ScheduleGroup")
                .websiteAddress("ScheduleWebsiteAddress")
                .memo("ScheduleMemo")
                .scheduleDate(LocalDateTime.of(2024, 2, 15, 1, 1))
                .users(user)
                .company(company)
                .build();

        return scheduleRepository.save(schedule);
    }

    private Company createCompany() {
        Company company = Company.builder()
                .name("CompanyName")
                .websiteAddress("CompanyWebsiteAddress")
                .memo("CompanyMemo")
                .build();
        return companyRepository.save(company);
    }

    private Users createUsers() {
        Users user = Users.builder()
                .uid("UserUid")
                .password("UserPassword")
                .nickname("UserNickname")
                .authority("ROLE_USER")
                .status(UserStatus.ACTIVE)
                .build();
        return usersRepository.save(user);
    }
}
