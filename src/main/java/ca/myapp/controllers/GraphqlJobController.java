package ca.myapp.controllers;

import ca.myapp.models.Job;
import ca.myapp.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
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
            e.printStackTrace();
            return null;
        }
    }

    @QueryMapping
    public List<Job> jobByTitle(@Argument(name = "title") String title) {
        try{
            return new ArrayList<>(jobRepository.findByTitle(title));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
