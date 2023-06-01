package ca.myapp.repositories;

import ca.myapp.models.Job;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
@EntityScan
public interface JobRepository extends JpaRepository<Job, Long> {
    // Add custom query methods if needed

}

