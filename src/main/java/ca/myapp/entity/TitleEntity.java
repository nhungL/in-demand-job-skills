// package ca.myapp.entity;

// import ca.myapp.dgs.graph.schema.Degree;
// import ca.myapp.dgs.graph.schema.Skill;
// import jakarta.persistence.*;
// import lombok.*;

// import java.util.List;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// @Table(name = "title_entity")
// public class TitleEntity {
//     @Id
//     @Column(name="title", nullable = false)
//     private String title;

//     @Column(name="min_salary")
//     private Double minSalary;

//     @Column(name="max_salary")
//     private Double maxSalary;

//     @Column(name="avg_salary")
//     private Double avgSalary;

//     @Column(name="count")
//     private Double count;

//     @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = SkillEntity.class)
//     private List<Skill> top10Skills;

//     @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = DegreeEntity.class)
//     private List<Degree> degrees;

//     public TitleEntity(String title, Double minSalary, Double maxSalary, Double avgSalary, Double count,
//             List<Degree> degrees) {
//         this.title = title;
//         this.minSalary = minSalary;
//         this.maxSalary = maxSalary;
//         this.avgSalary = avgSalary;
//         this.count = count;
//         this.degrees = degrees;
//     }

    
// }
