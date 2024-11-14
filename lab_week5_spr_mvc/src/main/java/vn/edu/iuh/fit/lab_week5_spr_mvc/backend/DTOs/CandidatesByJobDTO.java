package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CandidatesByJobDTO
{
    private Long candidateId;
    private String candidateName;
    private String candidateEmail;
    private String jobName;
    private String jobDescription;
    private String skillName;
    private String skillDescription;

    public CandidatesByJobDTO(Long candidateId, String candidateName, String candidateEmail, String jobName, String jobDescription) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.candidateEmail = candidateEmail;
        this.jobName = jobName;
        this.jobDescription = jobDescription;
    }

    public CandidatesByJobDTO(Long candidateId, String candidateName, String candidateEmail, String jobName, String jobDescription, String skillName, String skillDescription) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.candidateEmail = candidateEmail;
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
    }
}
