package site.jobiljeong.scheduler.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.jobiljeong.scheduler.dto.attachment.AttachmentResponse;
import site.jobiljeong.scheduler.dto.schedule.ScheduleReadRequest;
import site.jobiljeong.scheduler.dto.schedule.ScheduleReadResponse;
import site.jobiljeong.scheduler.dto.schedule.ScheduleSaveRequest;
import site.jobiljeong.scheduler.dto.schedule.ScheduleUpdateRequest;
import site.jobiljeong.scheduler.entity.Attachment;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Schedule;
import site.jobiljeong.scheduler.entity.Users;
import site.jobiljeong.scheduler.repository.attachment.AttachmentRepository;
import site.jobiljeong.scheduler.repository.company.CompanyRepository;
import site.jobiljeong.scheduler.repository.schedule.ScheduleQueryRepository;
import site.jobiljeong.scheduler.repository.schedule.ScheduleRepository;
import site.jobiljeong.scheduler.repository.users.UsersRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final UsersRepository usersRepository;
    private final CompanyRepository companyRepository;
    private final AttachmentRepository attachmentRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleQueryRepository scheduleQueryRepository;

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
    public void modifySchedule(ScheduleUpdateRequest scheduleUpdateRequest) {
        Schedule schedule = scheduleRepository.findById(scheduleUpdateRequest.getScheduleNo()).orElseThrow(
                () -> new IllegalArgumentException("일정 정보를 찾을 수 없습니다."));

        schedule.changeScheduleInfo(scheduleUpdateRequest);
    }

    /**
     * 일정 정보 단건 조회
     */
    public ScheduleReadResponse findSchedule(Long scheduleNo) {
        //일정 정보 조회
        Schedule schedule = scheduleRepository.findById(scheduleNo).orElseThrow(
                () -> new IllegalArgumentException("일정 정보를 찾을 수 없습니다."));
        //첨부 파일 목록 조회
        List<Attachment> attachmentList = attachmentRepository.findAllByScheduleId(scheduleNo);
        List<AttachmentResponse> attachmentResponseList = attachmentList.stream()
                .map(AttachmentResponse::of)
                .toList();
        return ScheduleReadResponse.of(schedule, attachmentResponseList);
    }

    /**
     * 일정 정보 목록 조회 (기간별 - 월별)
     */
    public List<ScheduleReadResponse> findScheduleList(Long userId, ScheduleReadRequest scheduleReadRequest) {
        return scheduleQueryRepository.findScheduleList(userId, scheduleReadRequest.getRequestDate());
    }
}
