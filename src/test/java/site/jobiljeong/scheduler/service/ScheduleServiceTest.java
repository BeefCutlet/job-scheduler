package site.jobiljeong.scheduler.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import site.jobiljeong.scheduler.dto.schedule.ScheduleReadResponse;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;
import site.jobiljeong.scheduler.repository.schedule.ScheduleRepository;
import site.jobiljeong.scheduler.repository.schedule.ScheduleRepositoryImpl;
import site.jobiljeong.scheduler.service.schedule.ScheduleService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepositoryImpl scheduleQueryRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @DisplayName("일정 단건 조회 성공")
    @Test
    void findScheduleSuccess() {
        Long scheduleNo = 1L;
        Schedule schedule = createSchedule();
        ReflectionTestUtils.setField(schedule, "id", 1L);

        when(scheduleRepository.findById(scheduleNo)).thenReturn(Optional.of(schedule));

        ScheduleReadResponse findSchedule = scheduleService.findSchedule(scheduleNo);

        assertThat(findSchedule.getScheduleType()).isEqualTo(ScheduleType.MEETING);
        assertThat(findSchedule.getScheduleGroup()).isEqualTo("ScheduleGroup");
        assertThat(findSchedule.getWebsiteAddress()).isEqualTo("ScheduleWebsiteAddress");
        assertThat(findSchedule.getMemo()).isEqualTo("ScheduleMemo");
        assertThat(findSchedule.getScheduleDate()).isEqualTo(LocalDateTime.of(2024, 3, 15, 1, 1));
        assertThat(findSchedule.getCompanyName()).isEqualTo("CompanyName");
    }

    @DisplayName("일정 단건 조회 실패 - 일정 없음")
    @Test
    void findScheduleNotExist() {
        Long scheduleNo = 1L;
        Schedule schedule = Schedule.builder()
                .scheduleType(ScheduleType.MEETING)
                .scheduleGroup("ScheduleGroup")
                .websiteAddress("ScheduleWebsiteAddress")
                .memo("ScheduleMemo")
                .scheduleDate(LocalDateTime.of(2024, 3, 15, 1, 1))
                .build();
        ReflectionTestUtils.setField(schedule, "id", 1L);

        when(scheduleRepository.findById(scheduleNo)).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(() -> scheduleService.findSchedule(scheduleNo)).isInstanceOf(IllegalArgumentException.class);
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
}
