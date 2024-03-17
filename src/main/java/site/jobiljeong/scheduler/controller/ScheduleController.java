package site.jobiljeong.scheduler.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.jobiljeong.scheduler.dto.schedule.ScheduleReadResponse;
import site.jobiljeong.scheduler.dto.schedule.ScheduleReadRequest;
import site.jobiljeong.scheduler.dto.schedule.ScheduleSaveRequest;
import site.jobiljeong.scheduler.dto.schedule.ScheduleUpdateRequest;
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
                                          @RequestBody ScheduleSaveRequest scheduleSaveRequest) {
        Long userId = (Long) session.getAttribute("userId");
        scheduleService.saveSchedule(userId, scheduleSaveRequest);
        return ResponseEntity.created(URI.create("/info")).build();
    }

    /**
     * 일정 정보 수정
     */
    @PutMapping("/{scheduleNo}")
    public ResponseEntity<?> modifySchedule(@PathVariable Long scheduleNo,
                                            @RequestBody ScheduleUpdateRequest scheduleUpdateRequest) {
        scheduleService.modifySchedule(scheduleNo, scheduleUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    /**
     * 일정 정보 단건 조회
     */
    @GetMapping("/{scheduleNo}")
    public ResponseEntity<ScheduleReadResponse> findSchedule(@PathVariable Long scheduleNo) {
        ScheduleReadResponse scheduleReadResponse = scheduleService.findSchedule(scheduleNo);
        return ResponseEntity.ok(scheduleReadResponse);
    }

    /**
     * 일정 정보 기간별 조회 (월별)
     * 기간 정보 : requestDate
     * 기간 포맷 : yyyy-MM-dd
     */
    @GetMapping("/list")
    public ResponseEntity<List<ScheduleReadResponse>> findScheduleList(HttpSession session,
                                                                       @RequestBody ScheduleReadRequest scheduleReadRequest) {
        Long userId = (Long) session.getAttribute("userId");
        List<ScheduleReadResponse> scheduleList = scheduleService.findScheduleList(userId, scheduleReadRequest);
        return ResponseEntity.ok(scheduleList);
    }
}
