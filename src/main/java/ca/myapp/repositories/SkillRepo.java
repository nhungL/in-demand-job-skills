package ca.myapp.repositories;

import ca.myapp.entity.SkillEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
@EntityScan(basePackages = "ca.myapp.entity")
public interface SkillRepo extends JpaRepository<SkillEntity, String> {
}