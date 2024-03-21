package site.jobiljeong.scheduler.util;

import org.springframework.web.multipart.MultipartFile;
import site.jobiljeong.scheduler.dto.FileInfo;

public interface FileService {

    FileInfo storeFile(MultipartFile file);
    void deleteFile(String filename);
}
