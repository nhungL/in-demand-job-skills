package ca.myapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Job {
    @Id
    private String id;
    private String description;
    private String[] skills;

    public Job() {
        // Default constructor
    }

    public Job(String id, String description, String[] skills) {
        this.id = id;
        this.description = description;
        this.skills = skills;
    }

    public static Job getById(String id) {
        if ("1".equals(id)) {
            return new Job("1", "Data Scientist", new String[]{"Python"});
        } else if ("2".equals(id)) {
            return new Job("2", "Software Engineer", new String[]{"Java"});
        } else if ("3".equals(id)) {
            return new Job("3", "Data Analyst", new String[]{"SQL"});
        }

        return null; // Return null if no matching Job found
    }

    // Getters and setters for all properties

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }
}

//public record Job(String id, String description, String[] skills) {
//
//    private static List<Job> jobs = Arrays.asList(
//            new Job("1", "Data Scientist",new String[]{"Python"}),
//            new Job("2", "Software Engineer", new String[]{"author-2"}),
//            new Job("3", "Data Analyst", new String[]{"job 3 skill set"})
//    );
//
//    public static Job getById(String id) {
//        return jobs.stream()
//                .filter(job -> job.id().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//}