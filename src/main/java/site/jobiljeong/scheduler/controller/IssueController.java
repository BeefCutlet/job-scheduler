package site.jobiljeong.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.jobiljeong.scheduler.dto.issue.IssueReadResponse;
import site.jobiljeong.scheduler.dto.issue.IssueSaveRequest;
import site.jobiljeong.scheduler.service.issue.IssueService;

import java.util.List;

@RestController
@RequestMapping("/issue")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    /**
     * 회사 관련 이슈 저장
     */
    @PostMapping
    public ResponseEntity<?> saveNewIssueAboutCompany(@RequestBody IssueSaveRequest issueSaveRequest) {
        issueService.saveIssue(issueSaveRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * 회사 관련 이슈 목록 조회
     */
    @GetMapping("/{companyNo}")
    public ResponseEntity<?> findIssueList(@PathVariable Long companyNo) {
        List<IssueReadResponse> issueList = issueService.findIssueList(companyNo);
        return ResponseEntity.ok(issueList);
    }
}
