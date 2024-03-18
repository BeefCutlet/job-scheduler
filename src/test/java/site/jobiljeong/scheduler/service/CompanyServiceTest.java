package site.jobiljeong.scheduler.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import site.jobiljeong.scheduler.dto.company.CompanyReadResponse;
import site.jobiljeong.scheduler.dto.issue.IssueReadResponse;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Issue;
import site.jobiljeong.scheduler.repository.company.CompanyRepository;
import site.jobiljeong.scheduler.service.company.CompanyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void findCompanySuccess() {
        Long companyNo = 1L;
        Company company = createCompany();

        when(companyRepository.findById(companyNo)).thenReturn(Optional.of(company));

        CompanyReadResponse findCompany = companyService.findCompanyInfo(companyNo);
        assertThat(findCompany.getName()).isEqualTo("CompanyName");
        assertThat(findCompany.getWebsiteAddress()).isEqualTo("CompanyWebsiteAddress");
        assertThat(findCompany.getMemo()).isEqualTo("CompanyMemo");

        List<IssueReadResponse> issues = findCompany.getIssues();
        assertThat(issues.size()).isEqualTo(2);
        for (IssueReadResponse issue : issues) {
            assertThat(issue.getOriginLink()).isEqualTo("IssueOriginLink");
            assertThat(issue.getMemo()).isEqualTo("IssueMemo");
        }
    }

    private Company createCompany() {
        List<Issue> issueList = createIssueList();
        return Company.builder()
                .name("CompanyName")
                .websiteAddress("CompanyWebsiteAddress")
                .memo("CompanyMemo")
                .issues(issueList)
                .build();
    }

    private List<Issue> createIssueList() {
        Issue issue1 = Issue.builder()
                .originLink("IssueOriginLink")
                .memo("IssueMemo")
                .build();

        Issue issue2 = Issue.builder()
                .originLink("IssueOriginLink")
                .memo("IssueMemo")
                .build();

        ArrayList<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);
        return issues;
    }
}
