package ca.myapp.controllers;

import ca.myapp.MockData;
import ca.myapp.dgs.graph.schema.Skill;
import ca.myapp.repositories.JobRepository;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DgsAutoConfiguration.class, SkillController.class})
public class SkillControllerIT {
    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @MockBean
    private JobRepository jobRepository;

    private List<Skill> mockSkillsList;

    @BeforeEach
    public void before() {
        when(jobRepository.findAll()).thenAnswer(invocation -> MockData.createMockJobEntity());
        this.mockSkillsList = MockData.createMockSkills();
    }
    @Test
    public void testGetAllSkillsWithoutSort() throws Exception {
        System.out.println("---- INTEGRATION TEST SKILL CONTROLLER ----");
        @Language("GraphQL") String query = "{ getAllSkills { skill }}";

        List<String> skills = dgsQueryExecutor.executeAndExtractJsonPath(
                query, "data.getAllSkills[*].skill");

        assertEquals(mockSkillsList.size(), skills.size());
    }

    @Test
    public void testGetAllJobsWithFilter() throws Exception {
        @Language("GraphQL")
        String queryAsc = "{ getAllSkills (orderBy: {percent: asc}) { skill }}";

        List<String> skillAsc = dgsQueryExecutor.executeAndExtractJsonPath(
                queryAsc, "data.getAllSkills[*].skill");

        List<String> expectedAscSort = mockSkillsList.stream()
                .sorted(Comparator.comparing(Skill::getPercent)
                        .thenComparing(Skill::getSkill))
                .map(Skill::getSkill)
                .collect(Collectors.toList());

        System.out.println("ASC ORDER - EXPECTED: " + expectedAscSort);
        System.out.println("ASC ORDER - ACTUAL: " + skillAsc);

        assertIterableEquals(expectedAscSort, skillAsc);

        @Language("GraphQL") String queryDesc = "{ getAllSkills (orderBy: {percent: desc}) { skill }}";
        List<String> skillDesc = dgsQueryExecutor.executeAndExtractJsonPath(
                queryDesc, "data.getAllSkills[*].skill");
        List<String> expectedDescSort = mockSkillsList.stream()
                .sorted(Comparator.comparing(Skill::getPercent).reversed()
                        .thenComparing(Skill::getSkill))
                .map(Skill::getSkill)
                .collect(Collectors.toList());

        assertIterableEquals(expectedDescSort, skillDesc);
    }
}
