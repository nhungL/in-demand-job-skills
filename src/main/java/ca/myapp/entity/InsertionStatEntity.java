package ca.myapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "insertion_stat")
public class InsertionStatEntity {
    @Id
    @Column(name="insertion_id")
    private Integer insertionId;

    @Column(name="updated_at")
    private String updatedAt;

    @Column(name="job_added")
    private Integer jobsAdded;

    @Column(name="total_jobs")
    private Integer totalJobs;
}
