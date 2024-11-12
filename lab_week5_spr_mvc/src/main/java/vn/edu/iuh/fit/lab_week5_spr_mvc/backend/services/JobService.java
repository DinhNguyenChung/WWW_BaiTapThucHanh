package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Candidate;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.CandidateSkill;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Job;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Skill;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.JobRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }
    public List<Job> getMatchingJobsForCandidate(Long candidateId) {
        return jobRepository.findJobsMatchingCandidateSkills(candidateId);
    }
    public List<Job> getJobsByCompany(Long companyId) {
        return jobRepository.findJobsByCompanyId(companyId);
    }

}
