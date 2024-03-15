package site.jobiljeong.scheduler.repository.company;

import org.springframework.data.jpa.repository.JpaRepository;
import site.jobiljeong.scheduler.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
