package ca.myapp.controllers.calculations;

import ca.myapp.entity.JobEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JobCount {
    public static Map<String, Long> countJobsByTitle(List<JobEntity> jobEntities) {
        return jobEntities.stream()
                .collect(Collectors.groupingBy(JobEntity::getTitle,
                        Collectors.counting())
                );
    }

    public static void countSkillOccurrences(Map<String, Integer> map, List<String> skillsList) {
        for (String skill : skillsList) {
            map.put(skill, map.getOrDefault(skill, 0) + 1);
        }
    }
}
