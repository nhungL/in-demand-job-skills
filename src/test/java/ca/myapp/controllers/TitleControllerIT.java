package ca.myapp.controllers;

import ca.myapp.MockData;
import ca.myapp.dgs.graph.schema.Title;
import ca.myapp.repositories.JobRepository;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DgsAutoConfiguration.class, TitleController.class})
public class TitleControllerIT {
    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @MockBean
    private JobRepository jobRepository;

    private List<Title> mockTitlesList;

    @BeforeEach
    public void before() {
        when(jobRepository.findAll()).thenAnswer(invocation -> MockData.createMockJobEntity());
        this.mockTitlesList = MockData.createMockTitles();
    }
    @Test
    public void testAllByTitleWithoutFilter() throws Exception {
        System.out.println("\n ---- INTEGRATION TEST TITLE CONTROLLER ----");

        @Language("GraphQL") String query = "{ allByTitle { title }}";

        List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(
                query, "data.allByTitle[*].title");

        assertEquals(mockTitlesList.size(), titles.size());
    }

    @Test
    public void testAllByTitleWithFilter() throws Exception {
        List<String> titles = new ArrayList<>();
        titles.add("Data Scientist");
        titles.add("Software Engineer");

        for (String input : titles){
            @Language("GraphQL")
            String query = "{ allByTitle (inputFilter: {title: \"" + input + "\"}) { count }}";

            List<Double> result = dgsQueryExecutor.executeAndExtractJsonPath(
                    query, "data.allByTitle[*].count");

            List<Double> expectedCount = mockTitlesList.stream()
                    .filter(title -> title.getTitle().equals(input)).map(Title::getCount).toList();

            System.out.println("\n***** JOB TITLE FILTER: " + input + " ******\n");
            System.out.println("EXPECTED: " + expectedCount + "\n");
            System.out.println("ACTUAL: " + result + '\n');

            assertIterableEquals(expectedCount, result);
        }
    }
}
