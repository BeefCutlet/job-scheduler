package site.jobiljeong.scheduler.dto.company;

import lombok.Getter;

@Getter
public class CompanyUpdateRequest {

    private Long companyNo;
    private String name;
    private String websiteAddress;
    private String memo;
}
