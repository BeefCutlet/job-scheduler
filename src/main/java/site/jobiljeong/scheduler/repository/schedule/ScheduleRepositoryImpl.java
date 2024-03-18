package site.jobiljeong.scheduler.repository.schedule;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import site.jobiljeong.scheduler.dto.schedule.ScheduleReadResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static site.jobiljeong.scheduler.entity.QCompany.company;
import static site.jobiljeong.scheduler.entity.QSchedule.schedule;
import static site.jobiljeong.scheduler.entity.QUsers.users;

@Slf4j
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
    public List<ScheduleReadResponse> findScheduleList(Long userId, LocalDate currentTime) {
        return queryFactory.select(Projections.constructor(ScheduleReadResponse.class,
                        schedule.scheduleType,
                        schedule.scheduleDate,
                        schedule.memo,
                        schedule.websiteAddress,
                        schedule.scheduleGroup,
                        company.name.as("companyName")))
                .from(schedule)
                .join(schedule.company, company)
                .where(users.id.eq(userId), compareMonth(currentTime))
                .fetch();
    }

    private BooleanExpression compareMonth(LocalDate currentTime) {
        if (currentTime == null) {
            return null;
        }
        LocalDateTime firstDay = currentTime.withDayOfMonth(1).atStartOfDay();
        LocalDateTime lastDay = currentTime.withDayOfMonth(currentTime.lengthOfMonth()).atTime(LocalTime.MAX);

        return schedule.scheduleDate.between(firstDay, lastDay);
    }
}
