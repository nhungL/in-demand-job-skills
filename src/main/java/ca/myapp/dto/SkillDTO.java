package ca.myapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SkillDTO {
    private Integer skillId;
    private String title;
    private String skill;
    private Integer countByTitle;
    private Double percByTitle;
    private Double overallPerc;

    public SkillDTO(String skill, Double overallPerc) {
        this.skill = skill;
        this.overallPerc = overallPerc;
    }
}
