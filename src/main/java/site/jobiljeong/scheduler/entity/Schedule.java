package site.jobiljeong.scheduler.entity;

import jakarta.persistence.*;
import lombok.*;
import site.jobiljeong.scheduler.dto.ScheduleUpdateRequest;
import site.jobiljeong.scheduler.entity.custom.ScheduleType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Schedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    private LocalDateTime scheduleDate;
    private String memo;
    private String websiteAddress;
    private String scheduleGroup;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Attachment> attachments = new ArrayList<>();

    public void changeScheduleInfo(ScheduleUpdateRequest scheduleUpdateRequest) {
        this.scheduleType = scheduleUpdateRequest.getScheduleType();
        this.scheduleDate = scheduleUpdateRequest.getScheduleDate();
        this.memo = scheduleUpdateRequest.getMemo();
        this.websiteAddress = scheduleUpdateRequest.getWebsiteAddress();
    }
}
