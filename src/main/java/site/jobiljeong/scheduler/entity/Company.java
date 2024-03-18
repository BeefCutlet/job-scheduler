package site.jobiljeong.scheduler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Company extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    private String name;
    private String websiteAddress;
    private String memo;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Issue> issues = new ArrayList<>();

    public void changeCompanyInfo(String name, String websiteAddress, String memo) {
        this.name = name;
        this.websiteAddress = websiteAddress;
        this.memo = memo;
    }
}
