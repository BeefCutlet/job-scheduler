package site.jobiljeong.scheduler.repository.company;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import site.jobiljeong.scheduler.dto.company.CompanyReadResponse;

import static site.jobiljeong.scheduler.entity.QCompany.company;

@Repository
public class CompanyRepositoryImpl implements CompanyQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CompanyRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public CompanyReadResponse findCompanyInfo(Long companyNo) {
        return queryFactory.select(Projections.constructor(CompanyReadResponse.class,
                        company.name,
                        company.websiteAddress,
                        company.memo))
                .from(company)
                .fetchOne();
    }
}
