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
//    public List<Job> findJobsBySkills(List<Skill> skills) {
//        return jobRepository.findBySkillsIn(skills);
//    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }
//    public List<Job> suggestJobsForCandidate(Long candidateId) {
//        // Lấy thông tin ứng viên theo ID
//        Candidate candidate = candidateRepository.findById(candidateId)
//                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));
//
//        // Lấy danh sách kỹ năng của ứng viên
//        Set<Skill> candidateSkills = candidate.getSkills();
//
//        // Tìm công việc phù hợp với kỹ năng của ứng viên
//        return jobRepository.findBySkillsIn(List.copyOf(candidateSkills));
//    }


}
