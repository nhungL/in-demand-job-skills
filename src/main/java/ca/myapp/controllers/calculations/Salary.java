package ca.myapp.controllers.calculations;

import ca.myapp.controllers.mapping.JobMapping;
import ca.myapp.dgs.graph.schema.Job;
import ca.myapp.entity.JobEntity;

import java.util.*;
import java.util.stream.Collectors;

public class Salary {
    public static List<Map<String, Double>> getMinMaxSalaries(List<JobEntity> jobEntities) {
        List<Job> allJobs = jobEntities.stream().map(JobMapping::mapJobEntityToJob).toList();

        List<Map<String, Double>> minMaxSalariesList = new ArrayList<>();
        Map<String, Double> minSalaryMap = new HashMap<>();
        Map<String, Double> maxSalaryMap = new HashMap<>();

        for (Job job : allJobs) {
            String title = job.getTitle();

            minSalaryMap.put(title, Double.POSITIVE_INFINITY);
            maxSalaryMap.put(title, Double.NEGATIVE_INFINITY);
        }
        for (Job job : allJobs) {
            String title = job.getTitle();
            if (job.getSalary() != null && !job.getSalary().isEmpty()) {
                Double minValue = job.getSalary().get(0);
                Double maxValue = job.getSalary().get(1);

                if (minValue < minSalaryMap.get(title)){
                    minSalaryMap.put(title, minValue);
                }
                if (maxValue > maxSalaryMap.get(title)){
                    maxSalaryMap.put(title, maxValue);
                }
            }
        }

        minMaxSalariesList.add(minSalaryMap);
        minMaxSalariesList.add(maxSalaryMap);
        return minMaxSalariesList;
    }

    public static Map<String, Double> calAvgSalary(List<JobEntity> jobEntities) {
        return jobEntities.stream()
                .filter(job -> job.getSalary() != null && job.getSalary().size() == 2)
                .collect(Collectors.groupingBy(JobEntity::getTitle,
                        Collectors.averagingDouble(job -> calculateAverage(job.getSalary()))
                ));
    }

    public static double calculateAverage(List<Double> salaryRange){
        double minSalary = salaryRange.get(0);
        double maxSalary = salaryRange.get(1);

        return (minSalary + maxSalary) / 2;
    }
}
