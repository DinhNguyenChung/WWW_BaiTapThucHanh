package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
