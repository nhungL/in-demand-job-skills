package ca.myapp.controllers.calculations;

import ca.myapp.controllers.mapping.JobMapping;
import ca.myapp.dgs.graph.schema.Job;
import ca.myapp.entity.JobEntity;

import java.util.*;
import java.util.stream.Collectors;

public class Skills {
    public static Map<String, Map<String, Double>> rankSkillsByTitle(List<JobEntity> jobEntities) {
        // RETURN {title: {rank of skill} ...}
        Map<String, Map<String, Double>> topSkillsMap = new HashMap<>();

        Map<String, Map<String, Double>> skillsByTitleMap = extractSkillsByTitle(jobEntities);

        // Sort by values in descending order and create a new LinkedHashMap
        for (String title : skillsByTitleMap.keySet()) {
            Map<String, Double> skillsPercent = skillsByTitleMap.get(title);
            Map<String, Double> sortedSkillMap = skillsPercent.entrySet().stream()
                    .sorted(Map.Entry.<String, Double> comparingByValue().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey,
                                            Map.Entry::getValue,
                                            (e1, e2) -> e1, LinkedHashMap::new));

            topSkillsMap.put(title,sortedSkillMap);
        }

        return topSkillsMap;
    }

    public static Map<String, Map<String, Double>> extractSkillsByTitle (List<JobEntity> jobEntities) {
        List<Job> allJobs = jobEntities.stream().map(JobMapping::mapJobEntityToJob).toList();

        //{title: [list of skills]}
        Map<String, List<String>> listOfSkillsByTitle = listOfSkillsByTitle(allJobs);

        //{title: {{skill: %}, {skill: %} ...}
        Map<String, Map<String, Double>> skillsByTitleMap = new HashMap<>();

        // Calculate % of occurrence for each skill: {{skill: %}, {skill: %} ...}
        for (String title: listOfSkillsByTitle.keySet()) {
            Map<String, Double> skillsPercentMap = calSkills(listOfSkillsByTitle.get(title));
            skillsByTitleMap.put(title, skillsPercentMap);
        }

        return skillsByTitleMap;
    }

    /**
    * function: listOfSKillsByTitle
     *
    * @param allJobs: List of Job objects
    * @return Map< String title, List<String> list of skills of that title>
    **/
    public static Map<String, List<String>> listOfSkillsByTitle(List<Job> allJobs){
        Map<String, List<String>> listOfSkills = new HashMap<>();
        for (Job job : allJobs) {
            String title = job.getTitle();

            // First time, create a new list for skills
            List<String> skills = listOfSkills.computeIfAbsent(
                    title, k -> new ArrayList<>());

            skills.addAll(job.getSkills());
        }
        return listOfSkills;
    }

    private static Map<String, Double> calSkills(List<String> listOfSkills) {

        // {skill: count}
        Map<String, Integer> skillsFreq = new HashMap<>();
        for (String skill : listOfSkills) {
            skillsFreq.put(skill, skillsFreq.getOrDefault(skill, 0) + 1);
        }

        return calSkillsPercent(skillsFreq);
    }

    public static Map<String, Double> calSkillsPercent (Map<String, Integer> skillsFreq) {
        int numSkills = skillsFreq.values().stream().reduce(0, Integer::sum);

        // {skill: %}
        Map<String, Double> skillsPercent = new HashMap<>();
        for (String skill : skillsFreq.keySet()) {
            int freq = skillsFreq.get(skill);
            double percentage = (freq * 100.0) / numSkills;
            skillsPercent.put(skill, percentage);
        }
        return skillsPercent;
    }
}
