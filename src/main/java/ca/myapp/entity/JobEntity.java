package ca.myapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs_info", indexes = {
    @Index(name = "index_title", columnList = "title", unique = false)
})
public class JobEntity {
    @Id
    @Column(name = "job_id")
    private Integer jobId;

    @Column(name = "api_job_id", length = 4000, unique=true)
    private String apiJobId;

    @Column(name = "title")
    private String title;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "location")
    private String location;

    @Column(name = "via")
    private String via;

    @Column(name = "description", length = 15000)
    private String description;

    @Column(name = "extensions")
    private String extensions;

    @Column(name = "remote_option")
    private Boolean remoteOption;

    @Column(name = "posted_at")
    private String postedAt;

    @Column(name = "schedule_type")
    private String scheduleType;
}