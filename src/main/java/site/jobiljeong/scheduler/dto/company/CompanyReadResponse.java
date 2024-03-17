package site.jobiljeong.scheduler.dto.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.jobiljeong.scheduler.entity.Issue;

import java.util.List;

@Getter
@AllArgsConstructor
public class CompanyReadResponse {

    private String name;
    private String websiteAddress;
    private String memo;
    private List<Issue> issues;
}
