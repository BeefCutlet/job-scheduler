package site.jobiljeong.scheduler.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Issue;
import site.jobiljeong.scheduler.repository.issue.IssueRepository;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class IssueTest {

    @Autowired
    private IssueRepository issueRepository;

    @Test
    void insertIssueTest() {
        Issue issue = createIssue();

        Issue savedIssue = issueRepository.save(issue);

        assertThat(savedIssue.getOriginLink()).isEqualTo("IssueOriginLink");
        assertThat(savedIssue.getMemo()).isEqualTo("IssueMemo");
    }

    private Issue createIssue() {
        Company company = createCompany();
        return Issue.builder()
                .originLink("IssueOriginLink")
                .memo("IssueMemo")
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
