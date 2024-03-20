package site.jobiljeong.scheduler.entity;

import jakarta.persistence.*;
import lombok.*;
import site.jobiljeong.scheduler.entity.custom.AttachmentCategory;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Attachment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AttachmentCategory category;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
