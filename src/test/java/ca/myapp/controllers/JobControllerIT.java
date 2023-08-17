package ca.myapp.controllers;

import ca.myapp.MockData;
import ca.myapp.repositories.JobRepository;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DgsAutoConfiguration.class, JobController.class})
public class JobControllerIT {
    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @MockBean
    private JobRepository jobRepository;

    @BeforeEach
    public void before() {
        when(jobRepository.findAll()).thenAnswer(invocation -> MockData.createMockJobEntity());
    }
    @Test
    public void testGetAllJobsWithNullFilter() throws Exception {
        @Language("GraphQL") String query = "{ getAllJobs { title }}";

        List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(
                query, "data.getAllJobs[*].title");

        assertEquals(3, titles.size());
    }

    @Test
    public void testGetAllJobsWithFilter() throws Exception {
        @Language("GraphQL") String query = "{ getAllJobs (inputFilter: {title: \"Data Scientist\"}) { title }}";

        List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(
                query, "data.getAllJobs[*].title");

        assertEquals(2, titles.size());
    }
}
