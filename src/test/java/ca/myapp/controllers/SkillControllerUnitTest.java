package ca.myapp.controllers;

import ca.myapp.MockData;
import ca.myapp.dgs.graph.schema.Skill;
import ca.myapp.dgs.graph.schema.SkillOrderByPercent;
import ca.myapp.dgs.graph.schema.Sort;
import ca.myapp.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillControllerUnitTest {
    @InjectMocks
    private SkillController skillController;

    @Mock
    private JobRepository jobRepository;

    private List<Skill> mockSkillsList;
    @BeforeEach
    public void before() {
        when(jobRepository.findAll()).thenAnswer(invocation -> MockData.createMockJobEntity());
        this.mockSkillsList = MockData.createMockSkills();
    }
    @Test
    public void testGetAllSkillsWithoutSort() {
        System.out.println("---- UNIT TEST SKILL CONTROLLER ----");

        List<Skill> result = skillController.getAllSkills(new SkillOrderByPercent());

        assertEquals(mockSkillsList.size(), result.size());
    }

    @Test
    public void testGetAllSkillsWithSort() {

        List<Skill> result1 = skillController.getAllSkills(new SkillOrderByPercent(Sort.asc));
        List<Skill> result2 = skillController.getAllSkills(new SkillOrderByPercent(Sort.desc));

        List<Skill> expectedAscSort = mockSkillsList.stream()
                .sorted(Comparator.comparing(Skill::getPercent)
                        .thenComparing(Skill::getSkill))
                .collect(Collectors.toList());

        List<Skill> expectedDescSort = mockSkillsList.stream()
                .sorted(Comparator.comparing(Skill::getPercent).reversed()
                        .thenComparing(Skill::getSkill))
                .collect(Collectors.toList());

        System.out.println("ASCENDING ORDER: " + "\n");
        System.out.println("EXPECTED: " + expectedAscSort + "\n");
        System.out.println("ACTUAL: " + result1 + "\n");

        System.out.println("----------");

        System.out.println("DESCENDING ORDER: " + "\n");
        System.out.println("EXPECTED: " + expectedDescSort + "\n");
        System.out.println("ACTUAL: " + result2 + "\n");

        assertIterableEquals(expectedAscSort, result1);
        assertIterableEquals(expectedDescSort, result2);
    }
}
