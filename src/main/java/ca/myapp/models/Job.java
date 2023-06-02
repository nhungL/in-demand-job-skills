package ca.myapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Arrays;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="skills")
    private String[] skills;

    public Job() {
        // Default constructor
    }

    public Job(Long id, String title, String[] skills) {
        this.id = id;
        this.title = title;
        this.skills = skills;
    }

    // Getters and setters for all properties

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Job [id=" + id + ", title=" + title + ", skills=" + Arrays.toString(skills) + "]";
    }
}
//    public static Job getById(String id) {
//        if ("1".equals(id)) {
//            return new Job("1", "Data Scientist", new String[]{"Python"});
//        } else if ("2".equals(id)) {
//            return new Job("2", "Software Engineer", new String[]{"Java"});
//        } else if ("3".equals(id)) {
//            return new Job("3", "Data Analyst", new String[]{"SQL"});
//        }
//
//        return null; // Return null if no matching Job found
//    }

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