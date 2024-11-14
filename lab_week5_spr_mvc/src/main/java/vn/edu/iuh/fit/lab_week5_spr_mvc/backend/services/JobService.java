package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs.JobSkillDTO;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs.JobWithSkillsDTO;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.*;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.JobRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.JobSkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }
    public List<Job> getMatchingJobsForCandidate(Long candidateId) {
        return jobRepository.findJobsMatchingCandidateSkills(candidateId);
    }
    public List<Job> getJobsByCompany(Long companyId) {
        return jobRepository.findJobsByCompanyId(companyId);
    }
    public void addSkillToRelevantJobs(Skill skill) {
        List<Job> jobsRequiringSkill = jobRepository.findJobsBySkillId(skill.getId());

        // Nếu chưa có công việc nào yêu cầu kỹ năng này, bạn có thể thêm một số công việc mặc định hoặc thực hiện logic cần thiết.
        if (jobsRequiringSkill.isEmpty()) {
            // Logic để thêm kỹ năng vào các công việc phù hợp
            for (Job job : jobRepository.findAll()) {
                JobSkill jobSkill = new JobSkill();
                jobSkill.setJob(job);
                jobSkill.setSkill(skill);
                // Bạn có thể set thêm các thông tin như skillLevel nếu cần.
                jobSkillRepository.save(jobSkill);
            }
        }
    }
    public List<Job> findJobsBySkillId(Long id){
        return jobRepository.findJobsBySkillId(id);
    }
    public List<JobWithSkillsDTO> getJobWithSkills(Long companyId) {
        List<Object[]> results = jobRepository.findJobSkillsByCompany(companyId);
        List<JobWithSkillsDTO> jobWithSkillsDTOs = new ArrayList<>();

        for (Object[] result : results) {
            String jobDesc = (String) result[0];
            String jobName = (String) result[1];
            String skillNames = (String) result[2];

            JobWithSkillsDTO dto = new JobWithSkillsDTO(jobDesc, jobName, skillNames);
            jobWithSkillsDTOs.add(dto);
        }

        return jobWithSkillsDTOs;
    }

}
