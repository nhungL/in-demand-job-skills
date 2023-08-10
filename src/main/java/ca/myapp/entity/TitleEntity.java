package ca.myapp.entity;

import ca.myapp.dgs.graph.schema.Degree;
import ca.myapp.dgs.graph.schema.Skill;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "title_entity")
public class TitleEntity {
    @Id
    @Column(name="title")
    private String title;

    @Column(name="min_salary")
    private Double minSalary;

    @Column(name="max_salary")
    private Double maxSalary;

    @Column(name="avg_salary")
    private Double avgSalary;

    @Column(name="count")
    private Double count;

//    @ElementCollection
//    @JdbcTypeCode(SqlTypes.JSON)
//    @Cascade(value = { CascadeType.ALL })
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = SkillEntity.class)
//    @CollectionTable(name = "skill_entity", joinColumns = @JoinColumn(name = "skill"))
    private List<Skill> topSkills;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = DegreeEntity.class)
    private List<Degree> degrees;

    public TitleEntity(String title, Double minSalary, Double maxSalary, Double avgSalary, Double count, List<Skill> topSkills, List<Degree> degrees) {
        this.title = title;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.avgSalary = avgSalary;
        this.count = count;
        this.topSkills = topSkills;
        this.degrees = degrees;
    }

    public TitleEntity(String title, Double avgSalary, Double count, List<Skill> topSkills, List<Degree> degrees) {
        this.title = title;
        this.avgSalary = avgSalary;
        this.count = count;
        this.topSkills = topSkills;
        this.degrees = degrees;
    }

    public TitleEntity(String title, Double avgSalary) {
        this.title = title;
        this.avgSalary = avgSalary;
    }

    public TitleEntity() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public void setAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
    }

    public void setJobCount(Double count) {
        this.count = count;
    }

    public void setTopSkills(List<Skill> topSkills) {
        this.topSkills = topSkills;
    }
}
