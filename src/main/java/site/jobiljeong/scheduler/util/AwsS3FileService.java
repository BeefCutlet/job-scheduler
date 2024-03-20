package site.jobiljeong.scheduler.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.jobiljeong.scheduler.exception.StoreFileFailedException;

@Service
public class AwsS3FileService implements FileService {

    /**
     * 스토리지에 파일 저장
     * Return : 저장된 파일의 URL
     */
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
