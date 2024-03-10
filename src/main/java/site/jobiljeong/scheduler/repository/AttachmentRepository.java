package site.jobiljeong.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.jobiljeong.scheduler.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
