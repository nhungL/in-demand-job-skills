package ca.myapp.resolvers;

import ca.myapp.models.Job;
import ca.myapp.repositories.JobRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobResolver implements GraphQLQueryResolver {

    private final JobRepository jobRepository;

    public JobResolver(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job jobById(DataFetchingEnvironment e) {
        Long id = Long.parseLong(e.getArgument("id"));
//        return jobRepository.findById(id).orElse(null);
        return jobRepository.findById(id).orElse(new Job(100L, "Title",new String[]{"Python"}));
    }

    public List<Job> getAllJobs(DataFetchingEnvironment e) {
        return jobRepository.findAll();
    }
}

//    @Override
//    public Job get(DataFetchingEnvironment environment) throws Exception {
//        String id = environment.getArgument("title");
//        return jobRepository.findById(Long.valueOf("id")).orElse(null);
//    }

//    public List<Job> getAllJobs() {
//        List<Job> jobs = new ArrayList<Job>();
//        jobs.add(new Job("1", "Data Scientist", new String[]{"Python"}));
//        return jobs;
////        return jobRepository.findAll();
//    }

