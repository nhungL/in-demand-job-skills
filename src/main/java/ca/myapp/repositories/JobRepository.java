package ca.myapp.repositories;

import ca.myapp.entity.JobEntity;

import java.util.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
@EntityScan(basePackages = "ca.myapp.entity")
public interface JobRepository extends JpaRepository<JobEntity, String> {
    @Query(value = "SELECT DISTINCT title FROM jobs_info", nativeQuery = true)
    List<String> getDistinctTitles();

    @Query(value = "SELECT DISTINCT title, Count(*) " + 
                    "FROM jobs_info " + 
                    "GROUP BY title;", nativeQuery = true)
    List<Object[]> countJobsByTitle();
}

