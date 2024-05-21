package ca.myapp.controllers.mapping;

import ca.myapp.dgs.graph.schema.Job;
import ca.myapp.entity.JobEntity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class JobMapping {
    public static Job mapJobEntityToJob(@NotNull JobEntity jobEntity) {
        Job job = new Job();
        job.setJobId(jobEntity.getJobId());
        job.setApiJobId(jobEntity.getApiJobId());
        job.setTitle(jobEntity.getTitle());
        job.setCompanyName(jobEntity.getCompanyName());
        job.setLocation(jobEntity.getLocation());
        job.setVia(jobEntity.getVia());
        job.setDescription(jobEntity.getDescription());
        job.setExtensions(jobEntity.getExtensions());
        job.setRemoteOption(jobEntity.getRemoteOption());
        job.setPostedAt(jobEntity.getPostedAt());
        job.setScheduleType(jobEntity.getScheduleType());
        return job;
    }

    @NotNull
    private static List<String> reformatString(List<String> stringList) {
        List<String> formattedStringList = new ArrayList<>();
        if (stringList != null) {
            for (String string : stringList) {
                if (!string.isEmpty()) {
                    String format = string.replace("'", "");
                    if (!format.isEmpty()) {
                        String formattedString = format.substring(0, 1).toUpperCase()
                                                + format.substring(1).toLowerCase();
                        formattedStringList.add(formattedString);
                    }
                }
            }
        }
        return formattedStringList;
    }
}
