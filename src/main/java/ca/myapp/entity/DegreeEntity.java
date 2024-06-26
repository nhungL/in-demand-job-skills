package ca.myapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="edu_degree")
public class DegreeEntity {
    @Id
    @Column(name="edu_degree_id")
    private Integer degreeId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobEntity job;

    @Column(name="title")
    private String title;

    @Column(name="degree", nullable = false)
    private String degree;
}
