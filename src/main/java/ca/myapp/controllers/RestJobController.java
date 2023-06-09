package ca.myapp.controllers;

import ca.myapp.models.Job;
import ca.myapp.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class RestJobController {
    @Autowired
    JobRepository jobRepository;

    @GetMapping("/")
    public String index() throws IOException {
        Resource resource = new ClassPathResource("static/index.html");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    @GetMapping("/all-jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        try {
            List<Job> jobs = new ArrayList<Job>(jobRepository.findAll());
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-jobs/{id}")
    public ResponseEntity<Job> jobById(@PathVariable Long id) {
        try {
            Job job = jobRepository.findById(id).orElse(null);
            return new ResponseEntity<>(job, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}