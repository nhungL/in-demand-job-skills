package ca.myapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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


    public void setInsertionId(Integer insertionId) {
        this.insertionId = insertionId;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setJobsAdded(Integer jobsAdded) {
        this.jobsAdded = jobsAdded;
    }

    public void setTotalJobs(Integer totalJobs) {
        this.totalJobs = totalJobs;
    }
}
