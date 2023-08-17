package ca.myapp.controllers;

import ca.myapp.MockData;
import ca.myapp.dgs.graph.schema.Job;
import ca.myapp.dgs.graph.schema.JobFilter;
import ca.myapp.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobControllerUnitTest {
    @InjectMocks
    private JobController jobController;

    @Mock
    private JobRepository jobRepository;

    @BeforeEach
    public void before() {
        when(jobRepository.findAll()).thenAnswer(invocation -> MockData.createMockJobEntity());
    }
    @Test
    public void testGetAllJobsWithNullFilter() {
        JobFilter nullFilter = new JobFilter("", null, null);

        // Call the method
        List<Job> result1 = jobController.getAllJobs(null);
        List<Job> result2 = jobController.getAllJobs(nullFilter);

        // Assertions
        assertEquals(3, result1.size());
        assertEquals(3, result2.size());
    }

    @Test
    public void testGetAllJobsWithFilter() {
        JobFilter titleFilter = new JobFilter("Data Scientist", null, null);
        JobFilter remoteFilter = new JobFilter(null, false, null);
        JobFilter scheduleFilter = new JobFilter("", null, "Contractor");

        List<Job> result1 = jobController.getAllJobs(titleFilter);
        List<Job> result2 = jobController.getAllJobs(remoteFilter);
        List<Job> result3 = jobController.getAllJobs(scheduleFilter);

        assertEquals(2, result1.size());
        assertEquals(3, result2.size());
        assertEquals(0, result3.size());
    }
}
