package site.jobiljeong.scheduler.repository.attachment;

import org.springframework.data.jpa.repository.JpaRepository;
import site.jobiljeong.scheduler.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
