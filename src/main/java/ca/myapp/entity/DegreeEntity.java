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
@Table(name="degree_entity")
public class DegreeEntity {
    @Id
    @Column(name="degree")
    private String degree;

    @Column(name="count")
    private int count;

    @Column(name="percent")
    private Double percent;

    public DegreeEntity(String degree, int count, Double percentage) {
        this.degree = degree;
        this.count = count;
        this.percent = percentage;
    }

    public DegreeEntity(String degree, Double percentage) {
        this.degree = degree;
        this.percent = percentage;
    }

    public DegreeEntity() {

    }

    public void setDegree(String skill) {
        this.degree = skill;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}
