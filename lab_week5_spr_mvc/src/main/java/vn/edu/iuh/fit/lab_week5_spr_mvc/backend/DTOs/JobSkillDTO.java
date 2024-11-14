package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs;

public class JobSkillDTO {
    private String jobDesc;
    private String jobName;
    private String skillDescription;
    private String skillName;

    // Constructor
    public JobSkillDTO(String jobDesc, String jobName, String skillDescription, String skillName) {
        this.jobDesc = jobDesc;
        this.jobName = jobName;
        this.skillDescription = skillDescription;
        this.skillName = skillName;
    }

    // Getters và Setters
    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
