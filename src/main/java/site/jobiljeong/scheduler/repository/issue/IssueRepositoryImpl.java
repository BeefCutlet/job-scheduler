package site.jobiljeong.scheduler.repository.issue;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import site.jobiljeong.scheduler.dto.issue.IssueReadResponse;

import java.util.List;

import static site.jobiljeong.scheduler.entity.QCompany.company;
import static site.jobiljeong.scheduler.entity.QIssue.issue;

@Repository
public class IssueRepositoryImpl implements IssueQueryRepository {

    private final JPAQueryFactory queryFactory;

    public IssueRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<IssueReadResponse> findIssueList(Long companyNo) {
        return queryFactory.select(Projections.constructor(IssueReadResponse.class,
                        issue.originLink,
                        issue.memo))
                .from(issue)
                .join(issue.company, company)
                .where(company.id.eq(companyNo))
                .fetch();
    }
}
