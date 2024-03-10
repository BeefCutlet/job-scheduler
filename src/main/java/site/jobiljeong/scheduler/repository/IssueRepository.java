package site.jobiljeong.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.jobiljeong.scheduler.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
