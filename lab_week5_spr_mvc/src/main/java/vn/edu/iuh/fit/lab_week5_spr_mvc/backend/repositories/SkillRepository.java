package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Skill;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill,Long> {
    List<Skill> findAll();
    Skill findBySkillName(String name);
    @Query(value = "SELECT \n" +
            "    js.skill_id,\n" +
            "    s.skill_name,\n" +
            "    s.skill_description,\n" +
            "    js.skill_level AS required_level,\n" +
            "    COALESCE(cs.skill_level, 0) AS candidate_level,\n" +
            "    CASE \n" +
            "        WHEN cs.skill_level IS NULL THEN 'Missing Skill'\n" +
            "        ELSE 'Needs Improvement'\n" +
            "    END AS status\n" +
            "FROM \n" +
            "    job_skill js\n" +
            "JOIN \n" +
            "    skill s ON js.skill_id = s.skill_id\n" +
            "LEFT JOIN \n" +
            "    candidate_skill cs ON js.skill_id = cs.skill_id AND cs.can_id = :can_id\n" +
            "WHERE \n" +
            "    js.job_id = :job_id\n" +
            "    AND (cs.skill_level IS NULL OR cs.skill_level < js.skill_level)\n",
            nativeQuery = true

    )
    List<Object[]> findSkillsMissing(@Param("can_id") Long can_id,@Param("job_id") Long job_id);

}
