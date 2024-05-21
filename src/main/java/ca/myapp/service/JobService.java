package ca.myapp.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.myapp.repositories.JobRepository;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Map<String, Long> getJobCountsByTitle() {
        List<Object[]> results = jobRepository.countJobsByTitle();
        Map<String, Long> jobCountMap = new HashMap<>();
        for (Object[] result : results) {
            String title = (String) result[0];
            Long count = ((Number) result[1]).longValue(); 
            jobCountMap.put(title, count);
        }
        return jobCountMap;
    }

    public List<String> getDistinctTitleNames() {
        return jobRepository.getDistinctTitles();
    }
}
