package site.jobiljeong.scheduler.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.jobiljeong.scheduler.dto.ScheduleInfo;
import site.jobiljeong.scheduler.dto.ScheduleInfoRequest;
import site.jobiljeong.scheduler.dto.ScheduleSaveRequest;
import site.jobiljeong.scheduler.dto.ScheduleUpdateRequest;
import site.jobiljeong.scheduler.service.schedule.ScheduleService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 현재 접속 중인 회원의 일정 저장
     */
    @PostMapping
    public ResponseEntity<?> saveSchedule(HttpSession session,
                                          ScheduleSaveRequest scheduleSaveRequest) {
        Long userId = (Long) session.getAttribute("userId");
        scheduleService.saveSchedule(userId, scheduleSaveRequest);
        return ResponseEntity.created(URI.create("/info")).build();
    }

    /**
     * 일정 정보 수정
     */
    @PutMapping("/{scheduleNo}")
    public ResponseEntity<?> modifySchedule(@PathVariable Long scheduleNo,
                                            ScheduleUpdateRequest scheduleUpdateRequest) {
        scheduleService.modifySchedule(scheduleNo, scheduleUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    /**
     * 일정 정보 단건 조회
     */
    @GetMapping("/{scheduleNo}")
    public ResponseEntity<ScheduleInfo> findSchedule(@PathVariable Long scheduleNo) {
        ScheduleInfo scheduleInfo = scheduleService.findSchedule(scheduleNo);
        return ResponseEntity.ok(scheduleInfo);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ScheduleInfo>> findScheduleList(HttpSession session,
                                                               ScheduleInfoRequest scheduleInfoRequest) {
        Long userId = (Long) session.getAttribute("userId");
        List<ScheduleInfo> scheduleList = scheduleService.findScheduleList(userId, scheduleInfoRequest);
        return ResponseEntity.ok(scheduleList);
    }
}
