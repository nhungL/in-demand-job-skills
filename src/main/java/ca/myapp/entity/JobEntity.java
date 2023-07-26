package ca.myapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs_all")
public class JobEntity {
    @Id
    @Column(name = "job_id", length = 4000)
    private String jobId;

    @Column(name = "title")
    private String title;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "location")
    private String location;

    @Column(name = "via")
    private String via;

    @Column(name = "description", length = 12000)
    private String description;

    @Column(name = "extensions")
    private String extensions;

    @Column(name = "remote_option")
    private Boolean remoteOption;

    @Column(name = "salary")
    private List<Double> salary;

//    @ElementCollection
//    @CollectionTable(name = "job_edu_degree", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "edu_degree")
//    @Cascade(value = { CascadeType.ALL })
    private List<String> eduDegree;

    @Column(name = "skills")
    private List<String> skills;

    @Column(name = "posted_at")
    private String postedAt;

    @Column(name = "schedule_type")
    private String scheduleType;
}