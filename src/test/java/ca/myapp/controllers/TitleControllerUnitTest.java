package ca.myapp.controllers;

import ca.myapp.MockData;
import ca.myapp.dgs.graph.schema.Title;
import ca.myapp.dgs.graph.schema.TitleFilter;
import ca.myapp.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TitleControllerUnitTest {
    @InjectMocks
    private TitleController titleController;

    @Mock
    private JobRepository jobRepository;

    private List<Title> mockTitlesList;
    @BeforeEach
    public void before() {
        when(jobRepository.findAll()).thenAnswer(invocation -> MockData.createMockJobEntity());
        this.mockTitlesList = MockData.createMockTitles();
    }
    @Test
    public void testAllByTitleWithoutFilter() {
        System.out.println("---- UNIT TEST TITLE CONTROLLER ----");

        List<Title> result = titleController.allByTitle(new TitleFilter());

        assertEquals(mockTitlesList.size(), result.size());
    }

    @Test
    public void testAllByTitleWithFilter() {
        List<String> titles = new ArrayList<>();
        titles.add("Data Scientist");
        titles.add("Software Engineer");

        for (String title : titles){
            List<Title> result = titleController.allByTitle(new TitleFilter(title));

            List<Title> mockTitle = mockTitlesList.stream()
                    .filter(job -> job.getTitle().equals(title)).toList();

            System.out.println("***** JOB TITLE FILTER: " + title + " ******\n");
            System.out.println("EXPECTED: " + mockTitle);
            System.out.println("ACTUAL: " + result + "\n");

            assertIterableEquals(mockTitle, result);
        }
    }
}
