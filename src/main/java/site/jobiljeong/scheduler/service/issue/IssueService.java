package site.jobiljeong.scheduler.service.issue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.jobiljeong.scheduler.dto.issue.IssueReadResponse;
import site.jobiljeong.scheduler.dto.issue.IssueSaveRequest;
import site.jobiljeong.scheduler.dto.issue.IssueUpdateRequest;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Issue;
import site.jobiljeong.scheduler.repository.company.CompanyRepository;
import site.jobiljeong.scheduler.repository.issue.IssueQueryRepository;
import site.jobiljeong.scheduler.repository.issue.IssueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final IssueQueryRepository issueQueryRepository;
    private final CompanyRepository companyRepository;

    /**
     * 회사 관련 이슈 정보 저장
     */
    public void saveIssue(IssueSaveRequest issueSaveRequest) {
        Company company = companyRepository.findById(issueSaveRequest.getCompanyNo()).orElseThrow(
                () -> new IllegalArgumentException("회사 정보를 찾을 수 없습니다."));

        issueRepository.save(issueSaveRequest.convertToEntity(company));
    }

    /**
     * 회사 관련 이슈 정보 수정
     */
    public void modifyIssue(IssueUpdateRequest issueUpdateRequest) {
        Issue issue = issueRepository.findById(issueUpdateRequest.getIssueNo()).orElseThrow(
                () -> new IllegalArgumentException("이슈 정보를 찾을 수 없습니다."));

        issue.changeIssueInfo(issueUpdateRequest.getOriginLink(), issueUpdateRequest.getMemo());
    }

    /**
     *  회사 관련 이슈 단건 삭제
     */
    public void deleteIssue(Long issueNo) {
        issueRepository.findById(issueNo).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 이슈 정보입니다."));

        issueRepository.deleteById(issueNo);
    }

    /**
     * 회사 관련 이슈 정보 목록 조회
     */
    public List<IssueReadResponse> findIssueList(Long companyNo) {
        return issueQueryRepository.findIssueList(companyNo);
    }
}
