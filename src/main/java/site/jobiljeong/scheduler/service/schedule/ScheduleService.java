package site.jobiljeong.scheduler.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.jobiljeong.scheduler.dto.ScheduleInfo;
import site.jobiljeong.scheduler.dto.ScheduleInfoRequest;
import site.jobiljeong.scheduler.dto.ScheduleSaveRequest;
import site.jobiljeong.scheduler.dto.ScheduleUpdateRequest;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.Users;
import site.jobiljeong.scheduler.repository.company.CompanyRepository;
import site.jobiljeong.scheduler.repository.schedule.ScheduleRepository;
import site.jobiljeong.scheduler.repository.users.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final UsersRepository usersRepository;
    private final ScheduleRepository scheduleRepository;
    private final CompanyRepository companyRepository;

    /**
     * 일정 등록 + 일정과 연관된 회사 등록 (이름만)
     */
    public void saveSchedule(Long userId, ScheduleSaveRequest scheduleSaveRequest) {
        Users user = usersRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        Company company = scheduleSaveRequest.createCompany();
        Schedule schedule = scheduleSaveRequest.createSchedule(user, company);

        companyRepository.save(company);
        scheduleRepository.save(schedule);
    }

    /**
     * 일정 정보 수정
     */
    public void modifySchedule(Long scheduleNo, ScheduleUpdateRequest scheduleUpdateRequest) {
        Schedule schedule = scheduleRepository.findById(scheduleNo).orElseThrow(
                () -> new IllegalArgumentException("일정 정보를 찾을 수 없습니다."));

        schedule.changeScheduleInfo(scheduleUpdateRequest);
    }

    /**
     * 일정 정보 단건 조회
     */
    public ScheduleInfo findSchedule(Long scheduleNo) {
        Schedule schedule = scheduleRepository.findById(scheduleNo).orElseThrow(
                () -> new IllegalArgumentException("일정 정보를 찾을 수 없습니다."));

        return new ScheduleInfo().createScheduleInfo(schedule);
    }

    /**
     * 일정 정보 목록 조회 (기간별 - 월별)
     */
    public List<ScheduleInfo> findScheduleList(Long userId, ScheduleInfoRequest scheduleInfoRequest) {
        List<ScheduleInfo> list = new ArrayList<>();
        return list;
    }
}
