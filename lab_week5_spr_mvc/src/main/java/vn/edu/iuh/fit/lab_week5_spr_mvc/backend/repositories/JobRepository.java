package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs.JobSkillDTO;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs.JobWithSkillsDTO;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Job;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Skill;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j FROM Job j " +
            "JOIN JobSkill js ON j.id = js.job.id " +
            "JOIN Skill s ON js.skill.id = s.id " +
            "JOIN CandidateSkill cs ON s.id = cs.skill.id " +
            "WHERE cs.can.id = :candidateId AND js.skillLevel <= cs.skillLevel")
    List<Job> findJobsMatchingCandidateSkills(@Param("candidateId") Long candidateId);
    List<Job> findJobsByCompanyId(Long companyId);

    @Query("SELECT DISTINCT j FROM Job j " +
            "JOIN JobSkill js ON j.id = js.job.id " +
            "JOIN Skill s ON js.skill.id = s.id " +
            "WHERE s.type = :type OR s.skillDescription LIKE CONCAT('%', :skillDescription, '%')")
    List<Job> findRelevantJobs(@Param("type") Byte type, @Param("skillDescription") String skillDescription);

    @Query("SELECT DISTINCT j FROM Job j " +
            "JOIN JobSkill js ON j.id = js.job.id " +
            "JOIN Skill s ON js.skill.id = s.id " +
            "WHERE s.id = :skillId")
    List<Job> findJobsBySkillId(@Param("skillId") Long skillId);
    //JPQL
//    @Query(value = "SELECT new vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs.JobSkillDTO(j.jobDesc, j.jobName, s.skillDescription, s.skillName) " +
//            "FROM Job j " +
//            "JOIN JobSkill js ON j.id = js.job.id " +
//            "JOIN Skill s ON js.skill.id = s.id " +
//            "WHERE j.company.id = :companyId")
//    List<JobSkillDTO> findJobSkillsByCompany(@Param("companyId") Long companyId);

    @Query(value = "SELECT  j.job_desc AS jobDesc, j.job_name AS jobName, GROUP_CONCAT(s.skill_name) AS skillNames " +
            "FROM job j " +
            "JOIN job_skill js ON j.job_id = js.job_id " +
            "JOIN skill s ON js.skill_id = s.skill_id " +
            "WHERE j.company = :companyId " +
            "GROUP BY j.job_desc, j.job_name",
            nativeQuery = true)
    List<Object[]> findJobSkillsByCompany(@Param("companyId") Long companyId);




}
