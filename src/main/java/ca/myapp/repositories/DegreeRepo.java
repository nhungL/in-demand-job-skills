package ca.myapp.repositories;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.myapp.entity.DegreeEntity;

@Repository
public interface DegreeRepo extends JpaRepository<DegreeEntity, Integer> {
    @Query(value = "WITH DegreeCounts AS (" + 
                    "SELECT degree, COUNT(*) AS degree_count " +
                    "FROM edu_degree " + 
                    "GROUP BY degree), " +
                    "TotalCount AS (" +
                    "SELECT COUNT(*) AS total_count " + 
                    "FROM edu_degree), " +
                    "DegreeStats AS (" +
                    "SELECT row_number() OVER (ORDER BY degree) AS degree_id, " +
                    "title, degree, COUNT(degree) AS count_by_title," +
                    "100.0 * COUNT(degree) / SUM(COUNT(degree)) OVER (PARTITION BY title) as perc_by_title " +
                    "FROM edu_degree " +
                    "GROUP BY title, degree) " +
                    "SELECT ds.degree_id, ds.title, ds.degree, ds.count_by_title, ds.perc_by_title, " + 
                    "(100.0 * dc.degree_count)/tc.total_count AS overall_perc " + 
                    "FROM DegreeStats ds " + 
                    "JOIN DegreeCounts dc ON ds.degree = dc.degree " + 
                    "CROSS JOIN TotalCount tc " + 
                    "WHERE ds.title = :title " +
                    "ORDER BY ds.degree",
                    nativeQuery = true)
    List<Object[]> getDegreeStatByTitle(@Param("title") String title);
}
