package site.jobiljeong.scheduler.util;

import org.springframework.web.multipart.MultipartFile;
import site.jobiljeong.scheduler.exception.StoreFileFailedException;

public class LocalFileService implements FileService {

    @Override
    public String storeFile(MultipartFile file) {
        boolean isSuccess = true;
        if (!isSuccess) {
            throw new StoreFileFailedException("스토리지에 파일을 저장하지 못했습니다.");
        }

        return null;
    }

    @Override
    public void deleteFile(String filename) {

    }
}
