package ca.myapp.controllers.mapping;

import ca.myapp.dgs.graph.schema.Job;
import ca.myapp.entity.JobEntity;
import org.jetbrains.annotations.NotNull;

public class JobMapping {
    public static Job mapJobEntityToJob(@NotNull JobEntity jobEntity) {
        Job job = new Job();
        job.setJobId(jobEntity.getJobId());
        job.setTitle(jobEntity.getTitle());
        job.setCompanyName(jobEntity.getCompanyName());
        job.setLocation(jobEntity.getLocation());
        job.setVia(jobEntity.getVia());
        job.setDescription(jobEntity.getDescription());
        job.setExtensions(jobEntity.getExtensions());
        job.setRemoteOption(jobEntity.getRemoteOption());
        job.setSalary(jobEntity.getSalary()); // Assuming Salary is a property of the JobEntity class
        job.setEduDegree(jobEntity.getEduDegree());
        job.setSkills(jobEntity.getSkills());
        job.setPostedAt(jobEntity.getPostedAt());
        job.setScheduleType(jobEntity.getScheduleType());
        return job;
    }
}
