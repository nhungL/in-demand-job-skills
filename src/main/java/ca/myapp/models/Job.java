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