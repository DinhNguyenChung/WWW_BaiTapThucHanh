package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
