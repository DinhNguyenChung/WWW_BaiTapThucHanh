package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Job;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.JobSkill;

import java.util.List;

public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {
//    List<Job> findJobSkillById(long id);
}
