package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs.SkillMissingDTO;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Skill;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.SkillRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
    public Skill findByName(String name) {
        return skillRepository.findBySkillName(name) ;
    }
    public List<SkillMissingDTO> getSkillMissingForCandidate(Long canid, Long jobid) {
        List<Object[]> rs = skillRepository.findSkillsMissing(jobid, canid);
        List<SkillMissingDTO> dtos = new ArrayList<SkillMissingDTO>();
        for (Object[] r : rs) {
            Long id = (Long) r[0];
            String skillName = (String) r[1];
            String skillDescription = (String) r[2];

            // Chuyển đổi an toàn từ Integer sang Byte
            Byte skillLevel = r[3] != null ? ((Number) r[3]).byteValue() : null;
            Byte canLevel = r[4] != null ? ((Number) r[4]).byteValue() : null;

            String status = (String) r[5];
            SkillMissingDTO dto = new SkillMissingDTO(id, skillName, skillDescription, skillLevel, canLevel, status);
            dtos.add(dto);
        }
        return dtos;
    }
    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

}
