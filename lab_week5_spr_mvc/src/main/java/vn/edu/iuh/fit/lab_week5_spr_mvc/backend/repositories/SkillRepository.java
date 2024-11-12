package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Skill;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill,Long> {
    List<Skill> findAll();
}
