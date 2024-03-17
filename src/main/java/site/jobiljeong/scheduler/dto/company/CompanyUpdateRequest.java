package site.jobiljeong.scheduler.dto.company;

import lombok.Getter;

@Getter
public class CompanyUpdateRequest {

    private String name;
    private String websiteAddress;
    private String memo;
}
