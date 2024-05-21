package ca.myapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="skill")
public class SkillEntity {
    @Id
    @Column(name="skill_id")
    private Integer skillId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobEntity job;

    @Column(name="title")
    private String title;

    @Column(name="skill", nullable = false)
    private String skill;
}
