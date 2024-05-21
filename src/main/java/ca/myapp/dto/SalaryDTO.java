package ca.myapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SalaryDTO {
    private String title;
    private Double minSalary;
    private Double maxSalary;
    private Double avgSalary;
}
