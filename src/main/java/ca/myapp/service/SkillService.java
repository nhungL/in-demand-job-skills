package ca.myapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.myapp.dto.SkillDTO;
import ca.myapp.repositories.SkillRepo;

@Service
public class SkillService {
    @Autowired
    private SkillRepo SkillRepo;

    public List<SkillDTO> getSkillStat() {
        List<Object[]> results = SkillRepo.getSkillStats();
        return convertToSkillDTO(results, false);
    }

    public List<SkillDTO> getSkillStatByTitle(String title) {
        List<Object[]> results = SkillRepo.getSkillStatByTitle(title);
        return convertToSkillDTO(results, true);
    }

    private List<SkillDTO> convertToSkillDTO(List<Object[]> results, boolean useTitleFilter) {
        List<SkillDTO> skillDTOs = new ArrayList<>();
        for (Object[] result : results) {
            SkillDTO skillDTO = new SkillDTO();
            if (useTitleFilter){
                Integer SkillId = ((Number) result[0]).intValue();
                String title = (String) result[1];
                String skill = (String) result[2];
                Integer countByTitle = ((Number) result[3]).intValue();
                Double percByTitle = ((Number) result[4]).doubleValue();
                Double overallPerc = ((Number) result[5]).doubleValue();

                skillDTO = new SkillDTO(SkillId, title, skill, countByTitle, percByTitle, overallPerc);
            } else {
                String skill = (String) result[0];
                Double overallPerc = ((Number) result[1]).doubleValue();
                skillDTO = new SkillDTO(skill, overallPerc);
            }

            skillDTOs.add(skillDTO);
        }
        return skillDTOs;
    }
}
