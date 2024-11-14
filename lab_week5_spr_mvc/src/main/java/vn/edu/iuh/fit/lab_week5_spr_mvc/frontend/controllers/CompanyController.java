package vn.edu.iuh.fit.lab_week5_spr_mvc.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs.JobWithSkillsDTO;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Candidate;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Company;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Job;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.CandidateServices;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.CompanyServices;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.JobService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/company")
public class CompanyController
{
    @Autowired
    private CompanyServices companyServices;
    @Autowired
    private JobService jobService;
    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public String companies( Model model){
        List<Company> companies = companyServices.findAll();
        model.addAttribute("company",companies);
        // Tạo một Map để lưu danh sách công việc theo công ty
        Map<Long, List<Job>> jobsByCompany = new HashMap<>();
        Map<Long,List<JobSkill>> jobSkillsByJob = new HashMap<>();
        for (Company company : companies) {
            jobsByCompany.put(company.getId(), jobService.getJobsByCompany(company.getId()));

        }


        model.addAttribute("jobsByCompany", jobsByCompany);

        return "companies/companys";
    }
    // Hiển thị form Edit
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Company company = companyServices.findCompanyById(id);
        System.out.println(company);
        model.addAttribute("company", company);
        model.addAttribute("UserName",company.getCompName());
        model.addAttribute("id",company.getId());
        model.addAttribute("emailHeader",company.getEmail());
        model.addAttribute("countries", CountryCode.values()); // Truyền danh sách quốc gia để chỉnh sửa
        return "companies/update-company"; // Đây là trang HTML để chỉnh sửa
    }
    @PostMapping("/company/edit/{id}")
    public String editCandidate(
            @PathVariable("id") Long id,
            @ModelAttribute Company company,
            Model model
    ) {
        // Tìm Company trong cơ sở dữ liệu
        Company existingCompany = companyServices.findCompanyById(id);

        // Lưu Address trước khi lưu Company nếu chưa có trong CSDL
        if (company.getAddress() != null) {
            // Lưu Address vào CSDL
            addressRepository.save(company.getAddress());
        }

        // Cập nhật thông tin của Candidate từ form
        existingCompany.setPhone(company.getPhone());
        existingCompany.setEmail(company.getEmail());
        existingCompany.setAbout(company.getAbout());
        existingCompany.setCompName(company.getCompName());
        existingCompany.setWebUrl(company.getWebUrl());
        existingCompany.setAddress(company.getAddress());


        // Cập nhật mã quốc gia từ countryCode (sử dụng CountryCode để lấy tên quốc gia)
        if (company.getAddress() != null && company.getAddress().getCountry() != null) {
            // Chuyển mã quốc gia từ Short thành CountryCode
            Short countryShort = company.getAddress().getCountry();
            CountryCode countryCode = CountryCode.getByCode(countryShort);

            if (countryCode != null) {
                // Lưu quốc gia vào Address
                company.getAddress().setCountry(countryShort);
            }
        }

        // Lưu Candidate đã chỉnh sửa vào CSDL
        companyRepository.save(existingCompany);
        model.addAttribute("company", company);
        model.addAttribute("UserName",company.getCompName());
        model.addAttribute("id",company.getId());
        model.addAttribute("emailHeader",company.getEmail());
        List<JobWithSkillsDTO> jobSkills = jobService.getJobWithSkills(company.getId());
        model.addAttribute("jobSkills", jobSkills);
//        model.addAttribute("candidates", companyRepository.findAll());
        return "companies/home-company"; // Chuyển hướng về danh sách Candidate
    }
    @PostMapping("/showCandidatesByJob")
    public String showCandidatesByJob(Model model,@RequestParam("jobName") String jobName,@RequestParam("emailHeader") String emailHeader,
                                      @RequestParam("NameCompany") String NameCompany) {
        model.addAttribute("emailHeader",emailHeader);
        model.addAttribute("UserName",NameCompany);
        Company company = companyServices.findCompanyByEmail(emailHeader);
        model.addAttribute("company", company);
        model.addAttribute("id",company.getId());
        model.addAttribute("jobName",jobName);
        model.addAttribute("candidatesByJob",candidateServices.getCandidatesByJobName(jobName));
        return "companies/candidatesByJob";
    }

}
