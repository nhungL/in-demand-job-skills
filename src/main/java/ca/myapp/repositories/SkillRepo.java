package ca.myapp.repositories;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.myapp.entity.SkillEntity;

@Repository
public interface SkillRepo extends JpaRepository<SkillEntity, Integer> {
    @Query(value = "WITH SkillCounts AS (" + 
                        "SELECT skill, COUNT(*) AS skill_count " +
                        "FROM skill " + 
                        "GROUP BY skill), " +
                    "TotalCount AS (" +
                        "SELECT COUNT(*) AS total_count " + 
                        "FROM skill), " +
                    "SkillStats AS (" +
                        "SELECT ROW_NUMBER() OVER (PARTITION BY title ORDER BY COUNT(skill) DESC) AS row_num, " +
                        "ROW_NUMBER() OVER (ORDER BY skill) AS skill_id, " +
                        "title, skill, COUNT(skill) AS count_by_title, " +
                        "100.0 * COUNT(skill) / SUM(COUNT(skill)) OVER (PARTITION BY title) AS perc_by_title " +
                        "FROM skill " +
                        "GROUP BY title, skill) " +
                    "SELECT ss.skill_id, ss.title, ss.skill, ss.count_by_title, ss.perc_by_title, " +
                    "(100.0 * sc.skill_count) / tc.total_count AS overall_perc " + 
                    "FROM SkillStats ss " + 
                    "JOIN SkillCounts sc ON ss.skill = sc.skill " + 
                    "CROSS JOIN TotalCount tc " + 
                    "WHERE ss.title = :title AND ss.row_num <= 10 " +
                    "ORDER BY ss.title, ss.count_by_title DESC",
                nativeQuery = true)
    List<Object[]> getSkillStatByTitle(@Param("title") String title);

    @Query(value = "WITH SkillCounts AS (" + 
                        "SELECT skill, COUNT(*) AS skill_count " +
                        "FROM skill " + 
                        "GROUP BY skill), " +
                    "TotalCount AS (" +
                        "SELECT COUNT(*) AS total_count " + 
                        "FROM skill) " +
                    "SELECT DISTINCT sc.skill, " +
                    "(100.0 * sc.skill_count) / tc.total_count AS overall_perc " + 
                    "FROM SkillCounts sc " + 
                    "CROSS JOIN TotalCount tc " + 
                    "ORDER BY overall_perc DESC",
                nativeQuery = true)
    List<Object[]> getSkillStats();
}
