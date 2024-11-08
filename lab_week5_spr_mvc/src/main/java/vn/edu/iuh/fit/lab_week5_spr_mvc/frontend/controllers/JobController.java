package vn.edu.iuh.fit.lab_week5_spr_mvc.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Candidate;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Job;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.CandidateServices;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.JobService;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CandidateServices candidateService;
//    @GetMapping("/suggestions/{candidateId}")
//    public List<Job> getSuggestedJobs(@PathVariable Long candidateId) {
//        Candidate candidate = candidateService.findCandidateById(candidateId); // Giả sử bạn có một service để tìm ứng viên
//        return jobService.suggestJobsForCandidate(candidate);
//    }
}
