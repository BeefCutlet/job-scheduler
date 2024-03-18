package site.jobiljeong.scheduler.dto.issue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueReadResponse {

    private String originLink;
    private String memo;
}
