package vn.edu.iuh.fit.lab_week5_spr_mvc.frontend.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.*;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.CandidateServices;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.CompanyServices;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.JobService;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.SkillService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CandidateServices candidateService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private LoginSignUpController loginSignUpController;
    @Autowired
    private CompanyServices companyServices;


    @GetMapping("/AddjobSkills")
    public String addJobSkills(Model model) {

        return "jobs/add-job-skills";
    }
    @PostMapping("/addJob")
    public String addJobWithSkills(
            @RequestParam("jobName") String jobName,
            @RequestParam("jobDesc") String jobDesc,
            @RequestParam("skillName") List<String> skillNames,
            @RequestParam("skillDes") List<String> skillDescs,
            @RequestParam("type") List<String> types,
            @RequestParam("skillLevel") List<String> skillLevels,
            @RequestParam("emailCompany") String emailCompany,
            org.springframework.ui.Model model) {

        try {
            // Lưu Job
            Job job = new Job();
            job.setJobName(jobName);
            job.setJobDesc(jobDesc);
            Company company = new Company();
            company = companyServices.findCompanyByEmail(emailCompany);
            job.setCompany(company);
            job = jobService.saveJob(job);

            // Lưu danh sách JobSkill
            List<JobSkill> jobSkills = new ArrayList<>();
            for (int i = 0; i < skillNames.size(); i++) {
                // Tìm hoặc tạo Skill
                String skillName = skillNames.get(i);
                String skillDes = skillDescs.get(i);
                String type = types.get(i);
                String skillLevel = skillLevels.get(i);

                Skill skill = skillService.findByName(skillName);
                if (skill == null) {
                    skill = new Skill();
                    skill.setSkillName(skillName);
                    skill.setSkillDescription(skillDes);
                    skill.setType(Byte.valueOf(type));
                    skill = skillService.saveSkill(skill);
                }

                // Tạo JobSkill
                JobSkill jobSkill = new JobSkill();
                jobSkill.setId(new JobSkillId(job.getId(), skill.getId()));
                jobSkill.setJob(job);
                jobSkill.setSkill(skill);
                jobSkill.setSkillLevel(Byte.valueOf(skillLevel));

                jobSkills.add(jobSkill);
            }

            // Lưu JobSkill
            jobService.saveJobSkills(jobSkills);

            // Truyền thông báo thành công tới giao diện
            model.addAttribute("message", "Job and skills added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while adding the job and skills.");
        }
       return loginSignUpController.login(emailCompany,model);
        // Redirect về trang danh sách hoặc trang chính
//        return "companies/home-company";
    }
}
