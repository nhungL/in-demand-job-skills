package ca.myapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.myapp.dto.DegreeDTO;
import ca.myapp.repositories.DegreeRepo;

@Service
public class DegreeService {
    @Autowired
    private DegreeRepo degreeRepo;

    public List<DegreeDTO> getDegreeStatByTitle(String title) {
        List<Object[]> results = degreeRepo.getDegreeStatByTitle(title);
        return convertToDegreeDTO(results);
    }

    private List<DegreeDTO> convertToDegreeDTO(List<Object[]> results) {
        List<DegreeDTO> degreeDTOs = new ArrayList<>();
        for (Object[] result : results) {
            Integer degreeId = ((Number) result[0]).intValue();
            String title = (String) result[1];
            String degree = (String) result[2];
            Integer countByTitle = ((Number) result[3]).intValue();
            Double percByTitle = ((Number) result[4]).doubleValue();
            Double overallPerc = ((Number) result[5]).doubleValue();

            DegreeDTO degreeDTO = new DegreeDTO(degreeId, title, degree, countByTitle, percByTitle, overallPerc);
            degreeDTOs.add(degreeDTO);
        }
        return degreeDTOs;
    }
}
