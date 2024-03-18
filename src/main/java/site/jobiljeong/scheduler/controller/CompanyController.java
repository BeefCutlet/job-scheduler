package site.jobiljeong.scheduler.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.jobiljeong.scheduler.dto.company.CompanyReadResponse;
import site.jobiljeong.scheduler.dto.company.CompanyUpdateRequest;
import site.jobiljeong.scheduler.service.company.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    /**
     * 회사 정보 수정
     */
    @PutMapping("/{companyNo}")
    public ResponseEntity<?> modifyCompanyInfo(@PathVariable Long companyNo,
                                               @RequestBody CompanyUpdateRequest companyUpdateRequest) {
        companyService.modifyCompanyInfo(companyNo, companyUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    /**
     * 회사 정보 단건 조회
     */
    @GetMapping("/{companyNo}")
    public ResponseEntity<CompanyReadResponse> findCompanyInfo(@PathVariable Long companyNo) {
        CompanyReadResponse company = companyService.findCompanyInfo(companyNo);
        return ResponseEntity.ok(company);
    }

    /**
     * 유저가 등록한 회사 정보 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<List<CompanyReadResponse>> findCompanyList(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        List<CompanyReadResponse> companyList = companyService.findCompanyList(userId);
        return ResponseEntity.ok(companyList);
    }
}
