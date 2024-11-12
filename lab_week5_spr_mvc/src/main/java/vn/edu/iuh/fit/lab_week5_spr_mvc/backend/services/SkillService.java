package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Skill;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.SkillRepository;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
}
