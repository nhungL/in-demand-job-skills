package ca.myapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DegreeDTO {
    private Integer degreeId;
    private String title;
    private String degree;
    private Integer countByTitle;
    private Double percByTitle;
    private Double overallPerc;
}
