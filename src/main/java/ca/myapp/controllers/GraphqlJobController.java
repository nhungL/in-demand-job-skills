package ca.myapp.controllers;

import ca.myapp.controllers.mapping.JobMapping;
import ca.myapp.dgs.graph.schema.Job;
import ca.myapp.entity.JobEntity;
import ca.myapp.repositories.JobRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

//@Controller
@DgsComponent
public class GraphqlJobController {
    @Autowired
    JobRepository jobRepository;

//    @QueryMapping
    @DgsQuery
    public List<Job> getAllJobs (){
        try {
            List<JobEntity> all_jobs = jobRepository.findAll();
            return all_jobs.stream().map(
                    JobMapping::mapJobEntityToJob)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DgsQuery
    public List<Job> jobByTitle(@InputArgument String title) {
        if (title == null){
            return getAllJobs();
        }

        try{
            List<JobEntity> job_by_title = jobRepository.findByTitle(title);
            return job_by_title.stream().map(
                    JobMapping::mapJobEntityToJob)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DgsQuery
    public List<Job> jobByRemoteOption(@InputArgument Boolean remote) {
        if (remote == null){
            return getAllJobs();
        }

        try{
            List<JobEntity> job_by_remote = jobRepository.findByRemoteOption(remote);
            return job_by_remote.stream().map(
                    JobMapping::mapJobEntityToJob)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
