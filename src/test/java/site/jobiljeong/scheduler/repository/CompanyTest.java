package site.jobiljeong.scheduler.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.repository.company.CompanyRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CompanyTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void insertCompanySuccess() {
        Company company = createCompany();

        Company savedCompany = companyRepository.save(company);

        assertThat(savedCompany.getName()).isEqualTo("CompanyName");
        assertThat(savedCompany.getMemo()).isEqualTo("CompanyMemo");
        assertThat(savedCompany.getWebsiteAddress()).isEqualTo("CompanyWebsiteAddress");
    }

    private Company createCompany() {
        return Company.builder()
                .name("CompanyName")
                .websiteAddress("CompanyWebsiteAddress")
                .memo("CompanyMemo")
                .build();
    }
}
