package ca.myapp.controllers;

import ca.myapp.models.Job;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

@Controller
public class JobController {
    @QueryMapping
    public Job jobById(@Argument String id) {
        return Job.getById(id);
    }

    @QueryMapping
    public List<Job> getAllJobs() {
        return new LinkedList<Job>();
    }
}