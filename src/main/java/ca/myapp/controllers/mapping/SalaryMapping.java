package ca.myapp.controllers.mapping;

import ca.myapp.dto.SalaryDTO;
import ca.myapp.entity.SalaryEntity;

public class SalaryMapping {
    // DTO to Entity
    public static SalaryEntity mapDTOToEntity(SalaryDTO salaryDTO) {
        SalaryEntity salaryEntity = new SalaryEntity();
        salaryEntity.setTitle(salaryDTO.getTitle());
        salaryEntity.setMinSalary(salaryDTO.getMinSalary());
        salaryEntity.setMaxSalary(salaryDTO.getMaxSalary());
        return salaryEntity;
    }

    // Entity to DTO
    public static SalaryDTO mapEntityToDTO(SalaryEntity salaryEntity) {
        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setTitle(salaryEntity.getTitle());
        salaryDTO.setMinSalary(salaryEntity.getMinSalary());
        salaryDTO.setMaxSalary(salaryEntity.getMaxSalary());
        salaryDTO.setAvgSalary((salaryEntity.getMinSalary() + salaryEntity.getMaxSalary()) / 2);
        return salaryDTO;
    }
}
