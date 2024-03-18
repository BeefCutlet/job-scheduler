package site.jobiljeong.scheduler.dto.company;

import lombok.Getter;
import site.jobiljeong.scheduler.dto.issue.IssueReadResponse;
import site.jobiljeong.scheduler.entity.Company;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CompanyReadResponse {

    private String name;
    private String websiteAddress;
    private String memo;
    private List<IssueReadResponse> issues;

    public CompanyReadResponse(String name, String websiteAddress, String memo) {
        this.name = name;
        this.websiteAddress = websiteAddress;
        this.memo = memo;
    }

    public CompanyReadResponse(Company company) {
        this.name = company.getName();
        this.websiteAddress = company.getWebsiteAddress();
        this.memo = company.getMemo();
        this.issues = company.getIssues().stream().map(
                issue -> new IssueReadResponse(issue.getOriginLink(), issue.getMemo())).collect(Collectors.toList());
    }
}
