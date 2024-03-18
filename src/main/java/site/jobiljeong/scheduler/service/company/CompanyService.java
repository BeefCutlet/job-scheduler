package site.jobiljeong.scheduler.service.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.jobiljeong.scheduler.dto.company.CompanyReadResponse;
import site.jobiljeong.scheduler.dto.company.CompanyUpdateRequest;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.repository.company.CompanyQueryRepository;
import site.jobiljeong.scheduler.repository.company.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyQueryRepository companyQueryRepository;

    /**
     * 회사 정보 수정
     */
    public void modifyCompanyInfo(Long companyNo, CompanyUpdateRequest companyUpdateRequest) {
        Company company = companyRepository.findById(companyNo).orElseThrow(
                () -> new IllegalArgumentException("회사 정보를 찾을 수 없습니다."));

        company.changeCompanyInfo(
                companyUpdateRequest.getName(),
                companyUpdateRequest.getWebsiteAddress(),
                companyUpdateRequest.getMemo());
    }

    /**
     * 회사 정보 단건 조회
     */
    public CompanyReadResponse findCompanyInfo(Long companyNo) {
        Company company = companyRepository.findById(companyNo).orElseThrow(
                () -> new IllegalArgumentException("회사 정보를 찾을 수 없습니다."));
        return new CompanyReadResponse(company);
    }

    /**
     * 사용자가 등록한 회사 정보 목록 조회
     */
    public List<CompanyReadResponse> findCompanyList(Long userId) {
        return companyQueryRepository.findCompanyList(userId);
    }

}
