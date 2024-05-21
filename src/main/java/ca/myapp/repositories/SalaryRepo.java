package ca.myapp.repositories;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.myapp.entity.SalaryEntity;

@Repository
public interface SalaryRepo extends JpaRepository<SalaryEntity, Integer> {
    @Query(value = "SELECT title, " +
                    "MIN(min_salary) AS min_salary, " + 
                    "MAX(max_salary) AS max_salary, " +
                    "ROUND(CAST(AVG((min_salary + max_salary) / 2) AS numeric), 2) AS avg_salary " +
                    "FROM salary " +
                    "GROUP BY title",
                    nativeQuery = true)
    List<Object[]> getSalaryStat();

    @Query(value = "SELECT title, " +
                   "MIN(min_salary) AS min_salary, " + 
                   "MAX(max_salary) AS max_salary, " +
                   "ROUND(CAST(AVG((min_salary + max_salary) / 2) AS numeric), 2) AS avg_salary " +
                   "FROM salary " +
                   "WHERE title = :title " +
                   "GROUP BY title",
           nativeQuery = true)
    List<Object[]> getSalaryStatByTitle(@Param("title") String title);
}
