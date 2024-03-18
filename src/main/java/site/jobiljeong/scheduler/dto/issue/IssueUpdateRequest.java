package site.jobiljeong.scheduler.dto.issue;

import lombok.Getter;

@Getter
public class IssueUpdateRequest {

    private Long issueNo;
    private String originLink;
    private String memo;
}
