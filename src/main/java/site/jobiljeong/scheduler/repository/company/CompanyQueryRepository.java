package site.jobiljeong.scheduler.repository.company;

import site.jobiljeong.scheduler.dto.company.CompanyReadResponse;

import java.util.List;

public interface CompanyQueryRepository {
    List<CompanyReadResponse> findCompanyList(Long userId);
}
