package vn.edu.iuh.fit.lab_week5_spr_mvc.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs.JobWithSkillsDTO;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.*;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private CompanyServices companyServices;
    @Autowired
    private JobService jobService;
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CompanyRepository companyRepository;


    @GetMapping("")
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

        return "index";
    }
    @GetMapping("/home/{id}")
    public String home(Model model,@PathVariable("id") Long  id,@RequestParam("emailHeader") String email){
//        Candidate candidate = candidateServices.findCandidateById(id);
//        System.out.println("jobNameHeader: "+jobName);
        Candidate candidate = candidateServices.findCandidateByEmail(email);
        if(candidate!=null){

            List<CandidateSkill> candidateSkills = candidateSkillService.getSkillsByCandidate(candidate.getId());
            model.addAttribute("CandidateSkill",candidateSkills );
            model.addAttribute("candidate",candidate);
            model.addAttribute("companies", companyServices.findAll());
            model.addAttribute("job",jobService.getMatchingJobsForCandidate(candidate.getId()));
            model.addAttribute("UserName",candidate.getFullName());
            model.addAttribute("id",candidate.getId());
            model.addAttribute("emailHeader",candidate.getEmail());
            model.addAttribute("Skills",skillService.getAllSkills());
            return "candidates/home-candidate";  // Redirect to another page, e.g., home.jsp or home.html
        }
//        Company company = companyServices.findCompanyById(id);
        Company company = companyServices.findCompanyByEmail(email);
        model.addAttribute("company", company);
        model.addAttribute("UserName",company.getCompName());
        model.addAttribute("id",company.getId());
        model.addAttribute("emailHeader",company.getEmail());
//        model.addAttribute("jobNameHeader",jobName);
        List<JobWithSkillsDTO> jobSkills = jobService.getJobWithSkills(company.getId());
        model.addAttribute("jobSkills", jobSkills);
//        model.addAttribute("candidatesByJob",candidateServices.getCandidatesByJobName(jobName));
        return "companies/home-company";

    }
}
