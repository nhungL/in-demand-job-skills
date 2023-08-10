package ca.myapp.controllers;

import ca.myapp.controllers.calculations.EduDegrees;
import ca.myapp.controllers.calculations.JobCount;
import ca.myapp.controllers.calculations.Salary;
import ca.myapp.controllers.calculations.Skills;
import ca.myapp.controllers.mapping.DegreeMapping;
import ca.myapp.controllers.mapping.SkillMapping;
import ca.myapp.controllers.mapping.TitleMapping;
import ca.myapp.dgs.graph.schema.Degree;
import ca.myapp.dgs.graph.schema.Skill;
import ca.myapp.dgs.graph.schema.Title;
import ca.myapp.dgs.graph.schema.TitleFilter;
import ca.myapp.entity.DegreeEntity;
import ca.myapp.entity.JobEntity;
import ca.myapp.entity.SkillEntity;
import ca.myapp.entity.TitleEntity;
import ca.myapp.repositories.JobRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@DgsComponent
public class TitleController {
    @Autowired
    JobRepository jobRepository;

    @DgsQuery
    public List<Title> allByTitle(@InputArgument("inputFilter")TitleFilter inputFilter) {
        try {
            List<JobEntity> jobEntities = jobRepository.findAll();

            Map<String, Double> minSalaryMap = Salary.getMinMaxSalaries(jobEntities).get(0);
            Map<String, Double> maxSalaryMap = Salary.getMinMaxSalaries(jobEntities).get(1);

            // Calculate average salary by title
            Map<String, Double> avgSalaryMap = Salary.calAvgSalary(jobEntities);

            // Count jobs by title
            Map<String, Long> jobCountMap = JobCount.countJobsByTitle(jobEntities);

            // Extract top skills by title
            Map<String, Map<String, Double>> topSkillsMap = Skills.rankSkillsByTitle(jobEntities);

            // Extract degree by title
            Map<String, Map<String, Double>> degreeMap = EduDegrees.rankDegreesByTitle(jobEntities);

            // Create TitleEntity objects
            List<TitleEntity> titleEntityList = new ArrayList<>();
            for (String title : avgSalaryMap.keySet()) {
                Double minSalary = minSalaryMap.get(title);
                Double maxSalary = maxSalaryMap.get(title);

                Double avgSalary = avgSalaryMap.get(title);

                Double jobCount = Double.valueOf(jobCountMap.getOrDefault(title, 0L));

                // Skills By Title
                List<SkillEntity> topSkillEntities = new ArrayList<>();
                Map<String, Double> skillsMap = topSkillsMap.get(title);
                skillsMap.forEach((key, value) -> {
                    SkillEntity skill = new SkillEntity();
                    skill.setSkill(key);
                    skill.setPercent(value);
                    topSkillEntities.add(skill);
                });
                List<Skill> topSkills = topSkillEntities.stream().map(SkillMapping::mapSkillEntityToSkill).toList();
//                System.out.println("TOP SKILLS: " + topSkills);

                // Degree By Title
                List<DegreeEntity> degreeEntities = new ArrayList<>();
                Map<String, Double> degreesMap = degreeMap.get(title);
                degreesMap.forEach((key, value) -> {
                    DegreeEntity degree = new DegreeEntity();
                    degree.setDegree(key);
                    degree.setPercent(value);
                    degreeEntities.add(degree);
                });
                List<Degree> degrees = degreeEntities.stream().map(DegreeMapping::mapDegreeEntityToDegree).toList();
//                System.out.println("DEGREE: " + degrees);


                TitleEntity titleEntity = new TitleEntity(title, minSalary, maxSalary, avgSalary, jobCount, topSkills, degrees);
                titleEntityList.add(titleEntity);
            }

            List<Title> allTitles = titleEntityList.stream().map(
                            TitleMapping::mapTitleEntityToTitle).toList();
            System.out.println("All By Title: " + allTitles);

            System.out.println("Input: " + inputFilter);
            // Return title summaries, no filter
            if (inputFilter == null) {
                return allTitles;
            }
            else {
                String title = inputFilter.getTitle();
                List<Predicate<Title>> predicates = new ArrayList<>();
                System.out.println("Filter: " + title);
                if (title == null || title.isEmpty()) {
                    System.out.println("Got null input");
                    return allTitles;
                }
                else {
                    System.out.println("Got input: " + title);
                    predicates.add(jobTitle -> jobTitle.getTitle().equals(title));
                    return allTitles.stream().filter(jobTitle -> predicates.stream().allMatch(p -> p.test(jobTitle)))
                            .collect(Collectors.toList());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
