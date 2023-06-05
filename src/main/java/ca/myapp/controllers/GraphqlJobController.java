package ca.myapp.controllers;

import ca.myapp.models.Job;
import ca.myapp.repositories.JobRepository;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GraphqlJobController {
    @Autowired
    JobRepository jobRepository;

    @QueryMapping
    public List<Job> getAllJobs (){
        try {
            return new ArrayList<Job>(jobRepository.findAll());
        } catch (Exception e) {
            return null;
        }
    }

    @QueryMapping
    public Job jobById(DataFetchingEnvironment environment) {
        Long id = Long.parseLong(environment.getArgument("id"));
        try {
            return jobRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
