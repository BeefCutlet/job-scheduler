package site.jobiljeong.scheduler.util;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String storeFile(MultipartFile file);
    void deleteFile(String filename);
}
