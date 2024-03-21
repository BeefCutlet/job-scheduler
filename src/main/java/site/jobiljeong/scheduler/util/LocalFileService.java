package site.jobiljeong.scheduler.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import site.jobiljeong.scheduler.dto.FileInfo;
import site.jobiljeong.scheduler.entity.Attachment;
import site.jobiljeong.scheduler.exception.StoreFileFailedException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class LocalFileService implements FileService {

    @Value("${user.file.path}")
    private String uploadDirectory;

    @PostConstruct
    public void init() {
        File directory = new File(uploadDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @Override
    public FileInfo storeFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String savedFilename = UUID.randomUUID() + originalFilename;
        String filePath = uploadDirectory + "/" + savedFilename;
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new StoreFileFailedException("스토리지에 파일을 저장하지 못했습니다.");
        }

        return FileInfo.builder()
                .originFilename(originalFilename)
                .savedFilename(savedFilename)
                .filePath(filePath)
                .build();
    }

    @Override
    public void deleteFile(String filename) {

    }
}
