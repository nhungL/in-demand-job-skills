package ca.myapp;

import ca.myapp.controllers.mapping.DegreeMapping;
import ca.myapp.controllers.mapping.SkillMapping;
import ca.myapp.controllers.mapping.TitleMapping;
import ca.myapp.dgs.graph.schema.Skill;
import ca.myapp.dgs.graph.schema.Title;
import ca.myapp.entity.DegreeEntity;
import ca.myapp.entity.JobEntity;
import ca.myapp.entity.SkillEntity;

import java.util.Arrays;
import java.util.List;

public class MockData {
        private static final JobEntity job1 = new JobEntity(
                1,
                "12345",
                "Software Engineer",
                "TechCo",
                "Remote",
                "LinkedIn",
                "We are seeking a skilled Software Engineer...",
                "Java, Python",
                false,
                "2023-05-15",
                "Full-Time"
        );

        private static final JobEntity job2 = new JobEntity(
                2,
                "54321",
                "Data Scientist",
                "Amazon",
                "Remote",
                "LinkedIn",
                "This is description",
                "extensions",
                false,
                "2023-05-18",
                "Part-Time"
        );

        private static final JobEntity job3 = new JobEntity(
                3,
                "12356",
                "Data Scientist",
                "ABC",
                "Remote",
                "LinkedIn",
                "We are seeking a skilled Software Engineer...",
                "this is extensions",
                false,
                "2023-05-15",
                "Full-Time"
        );

        public static List<JobEntity> createMockJobEntity() {
                return Arrays.asList(job1, job2, job3);
        }
        
        public static List<Skill> createMockSkills() {
                return Arrays.asList(
                        new SkillEntity(
                                1,
                                job1,
                                "Software Engineer",
                                "Java"
                        ),
                        new SkillEntity(
                                2, 
                                job2,
                                "Data Scientist", 
                                "Python"
                        ),
                        new SkillEntity(
                                3, 
                                job2,
                                "Data Scientist",
                                "Sql"
                        ),
                        new SkillEntity(
                                4, 
                                job2,
                                "Data Scientist",
                                "Docker"
                        ),
                        new SkillEntity(
                                5, 
                                job2,
                                "Data Scientist",
                                "Elf"
                        ),
                        new SkillEntity(
                        62, 
                                job2,
                                "Data Scientist",
                                "Cloud"
                        )
        ).stream().map(SkillMapping::mapSkillEntityToSkill).toList();
    }

    public static List<Title> createMockTitles() {
        return Arrays.asList(
                new TitleEntity(
                        "Software Engineer",
                        80000.0,
                        120000.0,
                        100000.0,
                        1.0,
                        Arrays.asList(
                                new SkillEntity(
                                    "Java",
                                    1,
                                    33.33
                                ),
                                new SkillEntity(
                                    "Python",
                                    1,
                                    33.33
                                ),
                                new SkillEntity(
                                    "Sql",
                                    1,
                                    33.33
                                )).stream().map(SkillMapping::mapSkillEntityToSkill).toList(),
                        Arrays.asList(
                                new DegreeEntity(
                                        "Bachelors",
                                        1,
                                        50.0
                                ),
                                new DegreeEntity(
                                        "Masters",
                                        1,
                                        50.0
                                )).stream().map(DegreeMapping::mapDegreeEntityToDegree).toList()
                ),
                new TitleEntity(
                        "Data Scientist",
                        60000.0,
                        200000.0,
                        125000.0,
                        2.0,
                        Arrays.asList(
                                new SkillEntity(
                                        "Python",
                                        2,
                                        33.33
                                ),
                                new SkillEntity(
                                        "Cloud",
                                        1,
                                        16.67
                                ),
                                new SkillEntity(
                                        "Docker",
                                        1,
                                        16.67
                                ),
                                new SkillEntity(
                                        "Elf",
                                        1,
                                        16.67
                                ),
                                new SkillEntity(
                                        "Sql",
                                        1,
                                        16.67
                                )
                        ).stream().map(SkillMapping::mapSkillEntityToSkill).toList(),
                        Arrays.asList(
                                new DegreeEntity(
                                        "Masters",
                                        2,
                                        66.67
                                ),
                                new DegreeEntity(
                                        "Bachelors",
                                        1,
                                        33.33
                                )
                        ).stream().map(DegreeMapping::mapDegreeEntityToDegree).toList()
                )
        ).stream().map(TitleMapping::mapTitleEntityToTitle).toList();
    }
}
