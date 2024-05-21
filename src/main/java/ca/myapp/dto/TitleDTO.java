package ca.myapp.dto;

import java.util.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TitleDTO {
    private String title;
    private Double minSalary;
    private Double maxSalary;
    private Double avgSalary;
    private Integer count;
    private List<SkillDTO> top10Skills;
    private List<DegreeDTO> eduDegree;
    
    public TitleDTO(String title, Double minSalary, Double maxSalary, Double avgSalary, Integer count,
            List<DegreeDTO> eduDegree) {
        this.title = title;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.avgSalary = avgSalary;
        this.count = count;
        this.eduDegree = eduDegree;
    }
}
