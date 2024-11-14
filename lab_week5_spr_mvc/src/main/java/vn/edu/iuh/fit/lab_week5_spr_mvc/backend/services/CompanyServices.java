package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Company;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CompanyRepository;

import java.util.List;

@Service
public class CompanyServices {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
    public Company findCompanyByEmail(String email) {
        return companyRepository.findCompanyByEmail(email);
    }
    public Company findCompanyById(long id) {
        return companyRepository.findById(id);
    }
}
