package site.jobiljeong.scheduler.repository.company;

import site.jobiljeong.scheduler.dto.company.CompanyReadResponse;

public interface CompanyQueryRepository {
    CompanyReadResponse findCompanyInfo(Long companyNo);
}
