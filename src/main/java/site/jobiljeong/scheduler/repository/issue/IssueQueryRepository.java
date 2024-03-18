package site.jobiljeong.scheduler.repository.issue;

import site.jobiljeong.scheduler.dto.issue.IssueReadResponse;

import java.util.List;

public interface IssueQueryRepository {

    List<IssueReadResponse> findIssueList(Long companyNo);
}
