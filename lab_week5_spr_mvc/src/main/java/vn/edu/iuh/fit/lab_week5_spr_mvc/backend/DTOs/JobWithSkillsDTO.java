package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public class JobWithSkillsDTO {
    private String jobDesc;
    private String jobName;
    private String skillNames;  // nhận kết quả của GROUP_CONCAT

    public JobWithSkillsDTO(String jobDesc, String jobName, String skillNames) {
        this.jobDesc = jobDesc;
        this.jobName = jobName;
        this.skillNames = skillNames;
    }

    // Chuyển đổi skillNames thành List<String>
    public List<String> getSkillNameList() {
        return Arrays.asList(skillNames.split(","));
    }
}
