package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Candidate;

import java.util.List;


public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    // Tìm tất cả các ứng viên với phân trang
    Page<Candidate> findAll(Pageable pageable);

    // Tìm ứng viên đầu tiên dựa trên sắp xếp
    Candidate findFirstByOrderByIdAsc();
    Candidate findFirstByOrderByIdDesc();

    // Tìm ứng viên top N theo sắp xếp (ví dụ, tìm top 5 theo tên)
    List<Candidate> findTop5ByOrderByFullNameAsc();
    List<Candidate> findTop5ByOrderByFullNameDesc();
    Candidate findById(long id);
    void deleteById(long id);
    Candidate findCandidatesByEmail(String email);
//    @Query(value = "SELECT DISTINCT c.id AS candidate_id,\n" +
//            "                c.full_name AS candidate_name,\n" +
//            "                c.email AS candidate_email,\n" +
//            "                j.job_name AS job_name,\n" +
//            "                j.job_desc AS job_description\n" +
//            "FROM candidate c\n" +
//            "JOIN candidate_skill cs ON c.id = cs.can_id\n" +
//            "JOIN job_skill js ON js.skill_id = cs.skill_id\n" +
//            "JOIN job j ON j.job_id = js.job_id\n" +
//            "WHERE cs.skill_level >= js.skill_level\n" +
//            "AND j.job_name = :jobName",
//            nativeQuery = true)
//    List<Object[]> findCandidatesByJob(@Param("jobName") String jobName);
@Query(value = "SELECT DISTINCT c.id AS candidate_id,\n" +
        "                c.full_name AS candidate_name,\n" +
        "                c.email AS candidate_email,\n" +
        "                j.job_name AS job_name,\n" +
        "                j.job_desc AS job_description,\n" +
        "                s.skill_name AS skill_name,\n" +
        "                s.skill_description AS skill_description\n" +
        "FROM candidate c\n" +
        "JOIN candidate_skill cs ON c.id = cs.can_id\n" +
        "JOIN job_skill js ON js.skill_id = cs.skill_id\n" +
        "JOIN job j ON j.job_id = js.job_id\n" +
        "JOIN skill s ON cs.skill_id = s.skill_id\n" +
        "WHERE cs.skill_level >= js.skill_level\n" +
        "AND j.job_name = :jobName",
        nativeQuery = true)
List<Object[]> findCandidatesByJob(@Param("jobName") String jobName);
}
