package site.jobiljeong.scheduler.dto.company;

import lombok.Builder;
import lombok.Getter;
import site.jobiljeong.scheduler.dto.issue.IssueReadResponse;
import site.jobiljeong.scheduler.entity.Company;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CompanyReadResponse {

    private String name;
    private String websiteAddress;
    private String memo;
    private List<IssueReadResponse> issues;

    public static CompanyReadResponse of(Company company) {
        return CompanyReadResponse.builder()
                .name(company.getName())
                .websiteAddress(company.getWebsiteAddress())
                .memo(company.getMemo())
                .issues(company.getIssues().stream().map(
                        issue -> new IssueReadResponse(issue.getOriginLink(), issue.getMemo())).collect(Collectors.toList()))
                .build();
    }
}
