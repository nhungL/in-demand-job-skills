package ca.myapp.controllers;

import ca.myapp.controllers.mapping.JobMapping;
import ca.myapp.dgs.graph.schema.Job;
import ca.myapp.dgs.graph.schema.JobFilter;
import ca.myapp.entity.JobEntity;
import ca.myapp.repositories.JobRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@DgsComponent
public class JobController {
    @Autowired
    JobRepository jobRepository;

    public JobController() {
        System.out.println("DGS Job Controller");
    }
    @DgsQuery
    public List<Job> getAllJobs(@InputArgument("inputFilter")JobFilter inputFilter) {
        if (inputFilter == null){
            List<JobEntity> allJobs = jobRepository.findAll();
            return allJobs.stream().map(
                            JobMapping::mapJobEntityToJob)
                    .collect(Collectors.toList());
        }

        try{
            String title = inputFilter.getTitle();
            Boolean remote = inputFilter.getRemoteOption();
            String workType = inputFilter.getScheduleType();

            // Create a list of predicates to apply filters
            List<Predicate<Job>> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(job -> job.getTitle().equals(title));
            }

            if (remote != null) {
                predicates.add(job -> job.getRemoteOption().equals(remote));
            }

            if (workType != null && !workType.isEmpty()) {
                predicates.add(job -> job.getScheduleType().equals(workType));
            }

            List<Job> allJobs = jobRepository.findAll().stream().map(JobMapping::mapJobEntityToJob).toList();

            return allJobs.stream().filter(job -> predicates.stream().allMatch(p -> p.test(job)))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
