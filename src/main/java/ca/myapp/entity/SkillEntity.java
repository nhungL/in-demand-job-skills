package ca.myapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Entity
@Table(name="skill_entity")
public class SkillEntity {
    @Id
    @Column(name="skill")
    private String skill;

    @Column(name="count")
    private int count;

    @Column(name="percent")
    private Double percent;

    public SkillEntity(String skill, int count, Double percentage) {
        this.skill = skill;
        this.count = count;
        this.percent = percentage;
    }

    public SkillEntity(String skill, Double percentage) {
        this.skill = skill;
        this.percent = percentage;
    }

    public SkillEntity() {

    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}
