package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs;

import java.util.List;

public class JobRequest {
    private String jobName;
    private String jobDes;
    private List<SkillRequest> skills;

    // Getters and Setters
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDes() {
        return jobDes;
    }

    public void setJobDes(String jobDes) {
        this.jobDes = jobDes;
    }

    public List<SkillRequest> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillRequest> skills) {
        this.skills = skills;
    }
}
