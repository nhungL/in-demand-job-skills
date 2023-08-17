package ca.myapp.controllers.calculations;

import ca.myapp.entity.JobEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Count {
    public static Map<String, Long> countJobsByTitle(List<JobEntity> jobEntities) {
        return jobEntities.stream()
                .collect(Collectors.groupingBy(JobEntity::getTitle,
                        Collectors.counting())
                );
    }

    public static void countOccurrences(Map<String, Integer> map, List<String> myList) {
        for (String item : myList) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
    }
}
