package ca.myapp.controllers;

import ca.myapp.Application;
import ca.myapp.MockData;
import ca.myapp.repositories.JobRepository;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.MonoGraphQLClient;
import com.netflix.graphql.dgs.client.WebClientGraphQLClient;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static graphql.Assert.assertTrue;
import static org.mockito.Mockito.when;


/*
* NAME: Web Layer Testing
* GOAL: Try to access graphql endpoint and get data from bean (mock data)
*       Test using JobController queries with filter and no filter
* REQUIREMENT: need to run Cloud SQL instance
* */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class WebLayerTest {
    private WebClientGraphQLClient monoGraphQLClient;

    @MockBean
    private JobRepository jobRepository;

    @BeforeEach
    public void setup (@LocalServerPort Integer port) {
        System.out.println("Setting Up!!!!!");
        WebClient webClient = WebClient.create("http://localhost:" + port + "/graphql");
        this.monoGraphQLClient = MonoGraphQLClient.createWithWebClient(webClient);
    }
    @BeforeEach
    public void before() {
        when(jobRepository.findAll()).thenAnswer(invocation -> MockData.createMockJobEntity());
    }

    @Test
    public void testGetAllJobs() throws Exception {
        System.out.println("In Web Layer Testing");
        @Language("graphql") String queryNoFilter = "{ getAllJobs { title }}";
        @Language("graphql") String queryWithFilter = "{ getAllJobs (inputFilter: {scheduleType: \"Full-Time\"}) { title }}";

        GraphQLResponse response1 = monoGraphQLClient.reactiveExecuteQuery(queryNoFilter).block();
        GraphQLResponse response2 = monoGraphQLClient.reactiveExecuteQuery(queryWithFilter).block();

        assert response1 != null;
        assert response2 != null;

        List<?> titles_1 = response1.extractValueAsObject("getAllJobs[*].title", List.class);
        List<?> titles_2 = response2.extractValueAsObject("getAllJobs[*].title", List.class);

        System.out.println("All Titles: " + titles_1);

        assertTrue(titles_1.contains("Data Scientist"));
        assertTrue(titles_2.contains("Software Engineer"));
    }
}