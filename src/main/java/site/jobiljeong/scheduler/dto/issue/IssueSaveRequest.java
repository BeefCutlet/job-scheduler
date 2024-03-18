package site.jobiljeong.scheduler.dto.issue;

import lombok.Getter;
import site.jobiljeong.scheduler.entity.Company;
import site.jobiljeong.scheduler.entity.Issue;

@Getter
public class IssueSaveRequest {

    private String originLink;
    private String memo;

    public Issue convertToEntity(Company company) {
        return Issue.builder()
                .originLink(this.originLink)
                .memo(this.memo)
                .company(company)
                .build();
    }
}
