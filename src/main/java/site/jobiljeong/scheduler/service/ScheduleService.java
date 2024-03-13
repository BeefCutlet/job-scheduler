package site.jobiljeong.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.jobiljeong.scheduler.dto.ScheduleInfo;
import site.jobiljeong.scheduler.dto.ScheduleSaveRequest;
import site.jobiljeong.scheduler.dto.ScheduleUpdateRequest;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.Users;
import site.jobiljeong.scheduler.repository.CompanyRepository;
import site.jobiljeong.scheduler.repository.ScheduleRepository;
import site.jobiljeong.scheduler.repository.UsersRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final UsersRepository usersRepository;
    private final ScheduleRepository scheduleRepository;
    private final CompanyRepository companyRepository;

    /**
     * 일정 등록 + 일정과 연관된 회사 등록 (이름만)
     * @param userId
     * @param scheduleSaveRequest
     */
    public void saveSchedule(Long userId, ScheduleSaveRequest scheduleSaveRequest) {
        Users user = usersRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        Company company = scheduleSaveRequest.createCompany();
        Schedule schedule = scheduleSaveRequest.createSchedule(user, company);

        companyRepository.save(company);
        scheduleRepository.save(schedule);
    }

    public void modifySchedule(Long scheduleNo, ScheduleUpdateRequest scheduleUpdateRequest) {
        Schedule schedule = scheduleRepository.findById(scheduleNo).orElseThrow(
                () -> new IllegalArgumentException("일정 정보를 찾을 수 없습니다."));

        schedule.changeScheduleInfo(scheduleUpdateRequest);
    }

    public ScheduleInfo findSchedule(Long scheduleNo) {
        Schedule schedule = scheduleRepository.findById(scheduleNo).orElseThrow(
                () -> new IllegalArgumentException("일정 정보를 찾을 수 없습니다."));
        return new ScheduleInfo().createScheduleInfo(schedule);
    }
}
