package site.jobiljeong.scheduler.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.jobiljeong.scheduler.dto.company.CompanyReadResponse;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Issue;
import site.jobiljeong.scheduler.entity.Users;
import site.jobiljeong.scheduler.repository.company.CompanyQueryRepository;
import site.jobiljeong.scheduler.repository.company.CompanyRepository;
import site.jobiljeong.scheduler.repository.issue.IssueRepository;
import site.jobiljeong.scheduler.repository.users.UsersRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class CompanyTest {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyQueryRepository companyQueryRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Test
    void insertCompanySuccess() {
        Users users = createUsers();
        Company company = createCompany(users);

        assertThat(company.getName()).isEqualTo("CompanyName");
        assertThat(company.getMemo()).isEqualTo("CompanyMemo");
        assertThat(company.getWebsiteAddress()).isEqualTo("CompanyWebsiteAddress");
    }

    @Test
    void findCompanySuccess() {
        Users users = createUsers();
        Company company = createCompany(users);
        createCompany(users);
        createCompany(users);
        Long userId = company.getUsers().getId();

        List<CompanyReadResponse> findCompanyList = companyQueryRepository.findCompanyList(userId);

        assertThat(findCompanyList.size()).isEqualTo(3);
        for (CompanyReadResponse findCompany : findCompanyList) {
            assertThat(findCompany.getName()).isEqualTo("CompanyName");
            assertThat(findCompany.getWebsiteAddress()).isEqualTo("CompanyWebsiteAddress");
            assertThat(findCompany.getMemo()).isEqualTo("CompanyMemo");
        }
    }

    private Company createCompany(Users users) {
        Company company = Company.builder()
                .name("CompanyName")
                .websiteAddress("CompanyWebsiteAddress")
                .memo("CompanyMemo")
                .users(users)
                .build();
        return companyRepository.save(company);
    }

    private Users createUsers() {
        Users user = Users.builder()
                .uid("UserUid")
                .password("UserPassword")
                .nickname("UserNickname")
                .authority("ROLE_USER")
                .build();
        return usersRepository.save(user);
    }
}
