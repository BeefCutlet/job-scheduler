package site.jobiljeong.scheduler.repository.schedule;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import site.jobiljeong.scheduler.dto.ScheduleInfo;
import site.jobiljeong.scheduler.repository.schedule.ScheduleQueryRepository;

import java.time.LocalDateTime;
import java.util.List;

import static site.jobiljeong.scheduler.entity.QCompany.company;
import static site.jobiljeong.scheduler.entity.QSchedule.schedule;
import static site.jobiljeong.scheduler.entity.QUsers.*;

@Repository
public class ScheduleRepositoryImpl implements ScheduleQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ScheduleRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 일정 목록 기간별 조회 (월별 조회)
     * startDate ~ endDate 사이의 유저의 일정
     */
    public List<ScheduleInfo> findScheduleList(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return queryFactory.select(Projections.constructor(ScheduleInfo.class,
                        schedule.scheduleType,
                        schedule.scheduleDate,
                        schedule.memo,
                        schedule.websiteAddress,
                        schedule.scheduleGroup,
                        company.name.as("companyName")))
                .from(schedule)
                .join(company)
                .on(schedule.company.id.eq(company.id))
                .where(users.id.eq(userId), schedule.scheduleDate.between(startDate, endDate))
                .fetch();
    }
}
