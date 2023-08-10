package ca.myapp.controllers.calculations;

import ca.myapp.controllers.mapping.JobMapping;
import ca.myapp.dgs.graph.schema.Job;
import ca.myapp.entity.JobEntity;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class EduDegrees {
    public static Map<String, Map<String, Double>> rankDegreesByTitle(List<JobEntity> jobEntities) {
        // RETURN {title: {rank of degree} ...}
        Map<String, Map<String, Double>> topDegreesMap = new HashMap<>();

        Map<String, Map<String, Double>> degreesByTitleMap = extractDegreesByTitle(jobEntities);

        // Sort by values in descending order and create a new LinkedHashMap
        for (String title : degreesByTitleMap.keySet()) {
            Map<String, Double> degreesPercent = degreesByTitleMap.get(title);
            Map<String, Double> sortedDegreeMap = degreesPercent.entrySet().stream()
                    .sorted(Map.Entry.<String, Double> comparingByValue().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));

            topDegreesMap.put(title,sortedDegreeMap);
        }

        return topDegreesMap;
    }

    public static Map<String, Map<String, Double>> extractDegreesByTitle (List<JobEntity> jobEntities) {
        List<Job> allJobs = jobEntities.stream().map(JobMapping::mapJobEntityToJob).toList();

        //{title: [list of degrees]}
        Map<String, List<String>> listOfDegreesByTitle = listOfDegreesByTitle(allJobs);

        //{title: {{degree: %}, {degree: %} ...}
        Map<String, Map<String, Double>> degreesByTitleMap = new HashMap<>();

        // Calculate % of occurrence for each degree: {{degree: %}, {degree: %} ...}
        for (String title: listOfDegreesByTitle.keySet()) {
            Map<String, Double> degreesPercentMap = calDegrees(listOfDegreesByTitle.get(title));
            degreesByTitleMap.put(title, degreesPercentMap);
        }

        return degreesByTitleMap;
    }

    public static Map<String, List<String>> listOfDegreesByTitle(@NotNull List<Job> allJobs){
        Map<String, List<String>> listOfDegrees = new HashMap<>();
        for (Job job : allJobs) {
            String title = job.getTitle();
            List<String> degreeList = job.getEduDegree();
//            System.out.println(title + degreeList);

            // First time, create a new list for degrees
            List<String> degrees = listOfDegrees.computeIfAbsent(
                    title, k -> new ArrayList<>());

            if (degreeList != null) {
                degrees.addAll(job.getEduDegree());
            }
        }
        return listOfDegrees;
    }

    @NotNull
    private static Map<String, Double> calDegrees(@NotNull List<String> listOfDegrees) {

        // {degree: count}
        Map<String, Integer> degreesFreq = new HashMap<>();
        for (String degree : listOfDegrees) {
            degreesFreq.put(degree, degreesFreq.getOrDefault(degree, 0) + 1);
        }

        return calDegreesPercent(degreesFreq);
    }

    @NotNull
    public static Map<String, Double> calDegreesPercent (@NotNull Map<String, Integer> degreesFreq) {
        int numDegrees = degreesFreq.values().stream().reduce(0, Integer::sum);

        // {degree: %}
        Map<String, Double> degreesPercent = new HashMap<>();
        for (String degree : degreesFreq.keySet()) {
            int freq = degreesFreq.get(degree);
            double percentage = (freq * 100.0) / numDegrees;
            degreesPercent.put(degree, percentage);
        }
        return degreesPercent;
    }
}
