package ca.myapp.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Table(name = "jobs_all")
public class Job {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "job_id", length = 4000)
    private String jobId;

    @Column(name = "title")
    private String title;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "location")
    private String location;

    @Column(name = "via")
    private String via;

    @Column(name = "description", length = 12000)
    private String description;

    @Column(name = "extensions")
    private String extensions;

    @Column(name = "remote_option")
    private Boolean remoteOption;

    @Column(name = "min_salary")
    private Double minSalary;

    @Column(name = "max_salary")
    private Double maxSalary;

    @Column(name = "avg_salary")
    private Double avgSalary;

    @ElementCollection
    @CollectionTable(name = "job_edu_degree", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "edu_degree", columnDefinition = "text[]")
    @Cascade(value = { CascadeType.ALL })
    private List<String> eduDegree;

    @ElementCollection
    @CollectionTable(name = "job_skills", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "skills")
    @Cascade(value = { CascadeType.ALL })
    private List<String> skills;

    @Column(name = "posted_at")
    private String postedAt;

    @Column(name = "schedule_type")
    private String scheduleType;

    // Constructors, getters, and setters

    public Job() {
        // Default constructor
    }

    // Constructor without ID field
    public Job(String id, String jobId, String title, String companyName, String location, String via, String description, String extensions,
               Boolean remoteOption, Double minSalary, Double maxSalary, Double avgSalary, List<String> eduDegree,
               List<String> skills, String postedAt, String scheduleType) {
        this.id = id;
        this.jobId = jobId;
        this.title = title;
        this.companyName = companyName;
        this.location = location;
        this.via = via;
        this.description = description;
        this.extensions = extensions;
        this.remoteOption = remoteOption;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.avgSalary = avgSalary;
        this.eduDegree = eduDegree;
        this.skills = skills;
        this.postedAt = postedAt;
        this.scheduleType = scheduleType;
    }

    public Job(String id, String jobId, String title) {
        this.id = id;
        this.jobId = jobId;
        this.title = title;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    public Boolean getRemoteOption() {
        return remoteOption;
    }

    public void setRemoteOption(Boolean remoteOption) {
        this.remoteOption = remoteOption;
    }

    public Double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
    }

    public List<String> getEduDegree() {
        return eduDegree;
    }

    public void setEduDegree(List<String> eduDegree) {
        this.eduDegree = eduDegree;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }
}