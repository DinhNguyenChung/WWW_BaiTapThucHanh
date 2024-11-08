package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Job;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Skill;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
//    @Query("SELECT j FROM Job j JOIN JobSkill js ON j.id = js.job.id WHERE js.skill.id = :skillId")
//    List<Job> findJobsBySkillId(@Param("skillId") Long skillId);
//    List<Job> findBySkillsIn(List<Skill> skills);
}
