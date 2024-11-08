package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Candidate;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.CandidateSkill;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CandidateSkillRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateSkillService {
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;


    public List<CandidateSkill> getSkillsByCandidate(Long candidateId) {
        return candidateSkillRepository.findByCan_Id(candidateId);
    }

    public void addCandidateSkill(CandidateSkill candidateSkill) {
        candidateSkillRepository.save(candidateSkill);
    }

    public List<Candidate> findCandidatesBySkill(Long skillId) {
        return candidateSkillRepository.findBySkill_Id(skillId)
                .stream()
                .map(CandidateSkill::getCan)
                .collect(Collectors.toList());
    }
}
