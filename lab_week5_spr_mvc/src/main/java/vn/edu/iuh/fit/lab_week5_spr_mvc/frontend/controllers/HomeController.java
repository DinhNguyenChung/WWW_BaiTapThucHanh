package vn.edu.iuh.fit.lab_week5_spr_mvc.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Company;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Job;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.CompanyServices;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.JobService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private CompanyServices companyServices;
    @Autowired
    private JobService jobService;
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
}
