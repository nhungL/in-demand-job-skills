package ca.myapp.resolvers;

import ca.myapp.models.Job;
import ca.myapp.repositories.JobRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobResolver implements DataFetcher<Job> {

    private final JobRepository jobRepository;

    public JobResolver(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job get(DataFetchingEnvironment environment) throws Exception {
        String id = environment.getArgument("title");
        return jobRepository.findById(Long.valueOf("id")).orElse(null);
    }


    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}

