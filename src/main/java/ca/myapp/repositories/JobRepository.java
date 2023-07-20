package ca.myapp.repositories;

import ca.myapp.models.Job;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
@EntityScan(basePackages = "ca.myapp.models")
public interface JobRepository extends JpaRepository<Job, Long> {
    /**
     * Query: find all jobs by title
     * @param title: String
     * @return list of jobs that has requested title
     */
    @Query("SELECT j FROM Job j WHERE j.title = :title")
    List<Job> findByTitle(@Param("title") String title);
}

