package ca.myapp.repositories;

import ca.myapp.entity.JobEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
@EntityScan(basePackages = "ca.myapp.entity")
public interface JobRepository extends JpaRepository<JobEntity, String> {
}

