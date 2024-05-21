package ca.myapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.myapp.dto.SalaryDTO;
import ca.myapp.repositories.SalaryRepo;

@Service
public class SalaryService {
    @Autowired
    private SalaryRepo salaryRepo;

    public List<SalaryDTO> getSalary(String title) {

        List<SalaryDTO> salaryStats = new ArrayList<>();
        List<Object[]> results;
        
        if (title == null || title.isEmpty()) {
            System.out.println("------NO TITLE FILTER-----");
            results = salaryRepo.getSalaryStat();
        } else {
            results = salaryRepo.getSalaryStatByTitle(title);
        }

        for (Object[] result : results) {
            String titleName = (String) result[0];
            Double minSalary = ((Number) result[1]).doubleValue();
            Double maxSalary = ((Number) result[2]).doubleValue();
            Double avgSalary = ((Number) result[3]).doubleValue();
            salaryStats.add(new SalaryDTO(titleName, minSalary, maxSalary, avgSalary));
        }

        return salaryStats;
    }
}
