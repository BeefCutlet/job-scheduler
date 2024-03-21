package site.jobiljeong.scheduler.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileInfo {

    private String originFilename;
    private String savedFilename;
    private String filePath;

}
