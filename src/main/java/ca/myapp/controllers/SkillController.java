package ca.myapp.controllers;

import ca.myapp.controllers.mapping.JobMapping;
import ca.myapp.controllers.mapping.SkillMapping;
import ca.myapp.dgs.graph.schema.Job;
import ca.myapp.dgs.graph.schema.Skill;
import ca.myapp.dgs.graph.schema.SkillOrderByPercent;
import ca.myapp.dgs.graph.schema.Sort;
import ca.myapp.entity.JobEntity;
import ca.myapp.entity.SkillEntity;
import ca.myapp.repositories.JobRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static ca.myapp.controllers.calculations.Count.countOccurrences;
import static ca.myapp.controllers.calculations.Skills.calSkillsPercent;
import static ca.myapp.controllers.calculations.Skills.listOfSkillsByTitle;

@DgsComponent
public class SkillController {
    @Autowired
    JobRepository jobRepository;

    public SkillController() {
        System.out.println("DGS Skill Controller");
    }
    @DgsQuery
    public List<Skill> getAllSkills(@InputArgument("orderBy") SkillOrderByPercent orderBy) {
        try {
            List<JobEntity> jobEntities = jobRepository.findAll();
            List<Job> allJobs = jobEntities.stream().map(JobMapping::mapJobEntityToJob).toList();

            Map<String, List<String>> listOfSkills = listOfSkillsByTitle(allJobs);

            Map<String, Integer> uniqueSkills = new HashMap<>();

            for (String title : listOfSkills.keySet()) {
                countOccurrences(uniqueSkills, listOfSkills.get(title));
            }


            Map<String, Double> skillPercent = calSkillsPercent(uniqueSkills);

            List<SkillEntity> skillEntityList = new ArrayList<>();
            for (String skill : uniqueSkills.keySet()) {
                int count = uniqueSkills.get(skill);

                DecimalFormat df = new DecimalFormat("#.##");
                Double percentage = Double.valueOf(df.format(skillPercent.get(skill)));
                SkillEntity titleEntity = new SkillEntity(skill, count, percentage);
                skillEntityList.add(titleEntity);
            }

            // Sorting the skillEntityList based on the orderBy argument
            if (orderBy != null && orderBy.getPercent() != null) {
                Sort order = orderBy.getPercent();
                if (order == Sort.asc) {
                    skillEntityList.sort(Comparator.comparing(SkillEntity::getPercent) //sort by percent
                            .thenComparing(SkillEntity::getSkill)); //then sort skill by alphabet
                } else if (order == Sort.desc) {
                    skillEntityList.sort(Comparator.comparing(SkillEntity::getPercent).reversed()
                            .thenComparing(SkillEntity::getSkill));
                }
            }

            // Return title summaries
            return skillEntityList.stream().map(
                            SkillMapping::mapSkillEntityToSkill)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
