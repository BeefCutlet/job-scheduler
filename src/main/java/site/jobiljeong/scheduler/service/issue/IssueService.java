package site.jobiljeong.scheduler.service.issue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.jobiljeong.scheduler.dto.issue.IssueReadResponse;
import site.jobiljeong.scheduler.dto.issue.IssueSaveRequest;
import site.jobiljeong.scheduler.entity.Company;
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
     * 회사 관련 이슈 정보 목록 조회
     */
    public List<IssueReadResponse> findIssueList(Long companyNo) {
        return issueQueryRepository.findIssueList(companyNo);
    }
}
