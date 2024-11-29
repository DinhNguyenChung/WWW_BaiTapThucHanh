package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs;

public class SkillRequest {
    private String skillName;
    private String skillDes;
    private String type;
    private String skillLevel;

    // Getters and Setters
    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDes() {
        return skillDes;
    }

    public void setSkillDes(String skillDes) {
        this.skillDes = skillDes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}
