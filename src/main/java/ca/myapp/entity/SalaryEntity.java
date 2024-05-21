package ca.myapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="salary")
public class SalaryEntity {
    @Id
    @Column(name="salary_id")
    private Integer degreeId;

    @OneToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobEntity job;

    @Column(name="title")
    private String title;

    @Column(name="min_salary", nullable = false)
    private Double minSalary;

    @Column(name="max_salary", nullable = false)
    private Double maxSalary;
}

    
