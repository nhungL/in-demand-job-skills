package ca.myapp.service;

import ca.myapp.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TitleService {
    @Autowired
    private JobService jobService;

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private DegreeService degreeService;

    @Autowired
    private SkillService skillService;

    public List<String> getDistinctTitles() {
        return jobService.getDistinctTitleNames();
    }
    public List<TitleDTO> getAllTitles(String title) {
        List<TitleDTO> titles = new ArrayList<>();

        Map<String, Long> jobCountMap = jobService.getJobCountsByTitle();

        // Fetch salary stats
        List<SalaryDTO> salaryStats = salaryService.getSalary(title);

        for (SalaryDTO stats : salaryStats) {
            TitleDTO titleDTO = new TitleDTO();

            String titleName = stats.getTitle();
            titleDTO.setTitle(titleName);

            titleDTO.setMinSalary(stats.getMinSalary());
            titleDTO.setMaxSalary(stats.getMaxSalary());
            titleDTO.setAvgSalary(stats.getAvgSalary());

            Integer jobCount = jobCountMap.get(titleName).intValue();
            titleDTO.setCount(jobCount);

            // Fetch degrees for the title
            List<DegreeDTO> degrees = degreeService.getDegreeStatByTitle(titleName);
            titleDTO.setEduDegree(degrees);

            // Fetch top 10 skills for the title
            List<SkillDTO> top10Skills = skillService.getSkillStatByTitle(titleName);
            titleDTO.setTop10Skills(top10Skills);

            titles.add(titleDTO);
        }

        return titles;
    }
}
