package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkillMissingDTO {
    private Long skillid;
    private String skillname;
    private String skilldesc;
    private Byte skilllevel;
    private Byte candidateLevel;
    private String status;

    public SkillMissingDTO(Long skillid, String skillname, String skilldesc, Byte skilllevel, Byte candidateLevel, String status) {
        this.skillid = skillid;
        this.skillname = skillname;
        this.skilldesc = skilldesc;
        this.skilllevel = skilllevel;
        this.candidateLevel = candidateLevel;
        this.status = status;
    }
}

