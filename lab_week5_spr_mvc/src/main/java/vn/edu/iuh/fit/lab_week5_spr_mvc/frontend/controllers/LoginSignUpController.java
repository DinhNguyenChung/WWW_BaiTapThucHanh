package vn.edu.iuh.fit.lab_week5_spr_mvc.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.converters.CountryCodeMapper;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.*;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class LoginSignUpController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    CompanyServices companyServices;
    @Autowired
    CandidateSkillService candidateSkillService;
    @Autowired
    private JobService jobService;
    @Autowired
    private AddressServices addressServices;
    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private SkillService skillService;

    @PostMapping("/login")
    public String login(@RequestParam String email, Model model) {
        try {
            Candidate candidate = candidateRepository.findCandidatesByEmail(email);
            if (candidate != null) {
                List<CandidateSkill> candidateSkills = candidateSkillService.getSkillsByCandidate(candidate.getId());
                model.addAttribute("CandidateSkill",candidateSkills );
                model.addAttribute("candidate",candidate);
                model.addAttribute("company", companyServices.findAll());
                model.addAttribute("job",jobService.getMatchingJobsForCandidate(candidate.getId()));
                model.addAttribute("UserName",candidate.getFullName());
                model.addAttribute("Skills",skillService.getAllSkills());
                return "candidates/home-candidate";  // Redirect to another page, e.g., home.jsp or home.html
            }

            else
            {
                Company company = companyServices.findCompanyByEmail(email);
                if (company != null) {
                    model.addAttribute("company", company);
                    model.addAttribute("UserName",company.getCompName());
                    return "companies/home-company";
                }
               else {
                    List<Company> companies = companyServices.findAll();
                    model.addAttribute("company",companies);
                    // Tạo một Map để lưu danh sách công việc theo công ty
                    Map<Long, List<Job>> jobsByCompany = new HashMap<>();
                    Map<Long,List<JobSkill>> jobSkillsByJob = new HashMap<>();
                    for (Company company1 : companies) {
                        jobsByCompany.put(company1.getId(), jobService.getJobsByCompany(company1.getId()));

                    }


                    model.addAttribute("jobsByCompany", jobsByCompany);
                    model.addAttribute("error", "Email không đúng. Vui lòng thử lại.");
                    return "index";  // Return to the login page with error message
                }
            }

        } catch (Exception e) {
            // If authentication fails
            model.addAttribute("error", "Đăng nhập không thành công. Vui lòng thử lại.");
            return "index";  // Return to the login page with error message
        }
    }
    @GetMapping("/addCandidates")
    public String addCandidatePage(Model model) {
//        model.addAttribute("add-candidates", candidateRepository.findAll());

        model.addAttribute("countryCodes", CountryCode.values());
        return "candidates/add-candidates";
    }
    @GetMapping("/addCompany")
    public String addCompanyPage(Model model) {
        model.addAttribute("countryCodes", CountryCode.values());
        return "companies/add-company";
    }
    @PostMapping("/addCandidate")
    public String addCandidate(
            @RequestParam("dob") String dob,
            @RequestParam("email") String email,
            @RequestParam("fullName") String fullName,
            @RequestParam("phone") String phone,
            @RequestParam("number") String number,
            @RequestParam("street") String street,
            @RequestParam("city") String city,
            @RequestParam("zipcode") String zipcode,
            @RequestParam("country") String countryCode,
            Model model
    ) {
       try {
           // Chuyển đổi dữ liệu từ form sang các đối tượng cần thiết
           LocalDate dateOfBirth = LocalDate.parse(dob);
           CountryCode country = CountryCode.valueOf(countryCode);
           Short countryShort = CountryCodeMapper.mapToShort(country);
           Address address = new Address(number, street, city, zipcode,countryShort);
           System.out.println("Address: "+address);
           Candidate candidate = new Candidate(dateOfBirth, email, fullName, phone, address);
           System.out.println("Candidate: "+candidate);
           // Lưu candidate vào cơ sở dữ liệu
           if (addressServices.saveAddress(address) != null) {
               candidateServices.saveCandidate(candidate);
           }
           model.addAttribute("message", "Sigup success !");
           List<Company> companies = companyServices.findAll();
           model.addAttribute("company",companies);
           // Tạo một Map để lưu danh sách công việc theo công ty
           Map<Long, List<Job>> jobsByCompany = new HashMap<>();
           Map<Long,List<JobSkill>> jobSkillsByJob = new HashMap<>();
           for (Company company1 : companies) {
               jobsByCompany.put(company1.getId(), jobService.getJobsByCompany(company1.getId()));

           }
           model.addAttribute("jobsByCompany", jobsByCompany);
           return "index"; // Chuyển hướng về trang danh sách candidates (hoặc bất kỳ trang nào khác)
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
    }
}
