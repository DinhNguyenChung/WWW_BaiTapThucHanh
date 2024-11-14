package vn.edu.iuh.fit.lab_week5_spr_mvc.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.*;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private CandidateSkillService candidateSkillService;
    @Autowired
    private AddressServices addressServices;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates";
    }
    @GetMapping("/candidate")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage= candidateServices.findAll(
                currentPage - 1,pageSize,"id","asc");
        model.addAttribute("candidatePage", candidatePage);
        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/candidates-paging";
    }
//    @PostMapping("/addSkill")
//    public ResponseEntity<String> addSkillToCandidate(@RequestParam Long candidateId, @RequestParam Long skillId, @RequestParam Byte skillLevel, @RequestParam String moreInfos) {
//        CandidateSkillId candidateSkillId = new CandidateSkillId(candidateId, skillId);
//        CandidateSkill candidateSkill = new CandidateSkill();
//        candidateSkill.setId(candidateSkillId);
//        candidateSkill.setSkillLevel(skillLevel);
//        candidateSkill.setMoreInfos(moreInfos);
//        candidateSkillService.addCandidateSkill(candidateSkill);
//        return ResponseEntity.ok("Skill added to candidate successfully.");
//    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<List<CandidateSkill>> getSkillsByCandidate(@PathVariable Long id) {
        List<CandidateSkill> skills = candidateSkillService.getSkillsByCandidate(id);
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/suggestSkills/{candidateId}")
    public ResponseEntity<List<Skill>> suggestSkills(@PathVariable Long candidateId) {
        // Logic đề xuất kỹ năng cho ứng viên dựa trên các yêu cầu công việc và kỹ năng hiện tại của ứng viên
        // Placeholder: giả định có một danh sách các kỹ năng để đề xuất
        List<Skill> suggestedSkills = new ArrayList<>();
        return ResponseEntity.ok(suggestedSkills);
    }
    @GetMapping("/addCandidates")
    public String addCandidateList(Model model) {
//        model.addAttribute("add-candidates", candidateRepository.findAll());
        return "candidates/add-candidates";
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
        // Chuyển đổi dữ liệu từ form sang các đối tượng cần thiết
        LocalDate dateOfBirth = LocalDate.parse(dob);
        CountryCode country = CountryCode.valueOf(countryCode);

        Address address = new Address(number, street, city, zipcode, (short) country.getNumeric());
        addressServices.saveAddress(address);
        Candidate candidate = new Candidate(dateOfBirth, email, fullName, phone, address);

        // Lưu candidate vào cơ sở dữ liệu
        candidateServices.saveCandidate(candidate);
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates"; // Chuyển hướng về trang danh sách candidates (hoặc bất kỳ trang nào khác)
    }
    // Hiển thị form Edit
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Candidate candidate = candidateServices.findCandidateById(id);
        System.out.println(candidate);
        model.addAttribute("candidate", candidate);
        model.addAttribute("UserName",candidate.getFullName());
        model.addAttribute("id",candidate.getId());
        model.addAttribute("emailHeader",candidate.getEmail());
        model.addAttribute("countries", CountryCode.values()); // Truyền danh sách quốc gia để chỉnh sửa
        return "candidates/update-candidates"; // Đây là trang HTML để chỉnh sửa
    }

//@PostMapping("/candidates/edit/{id}")
//public String editCandidate(
//        @PathVariable("id") Long id,
//        @ModelAttribute Candidate candidate,
//        Model model
//) {
//    // Tìm candidate trong cơ sở dữ liệu
//    Candidate existingCandidate = candidateServices.findCandidateById(id);
//
//    // Lưu Address trước khi lưu Candidate nếu chưa có trong CSDL
//    if (candidate.getAddress() != null) {
//        addressRepository.save(candidate.getAddress()); // Lưu Address vào CSDL
//    }
//
//    // Cập nhật thông tin của Candidate từ form
//    existingCandidate.setDob(candidate.getDob());
//    existingCandidate.setEmail(candidate.getEmail());
//    existingCandidate.setFullName(candidate.getFullName());
//    existingCandidate.setPhone(candidate.getPhone());
//    existingCandidate.setAddress(candidate.getAddress());
//
//    // Lưu Candidate đã chỉnh sửa vào CSDL
//    candidateServices.saveCandidate(existingCandidate);
//
//    model.addAttribute("candidates", candidateRepository.findAll());
//    return "candidates/candidates"; // Chuyển hướng về danh sách Candidate
//}
@PostMapping("/candidates/edit/{id}")
public String editCandidate(
        @PathVariable("id") Long id,
        @ModelAttribute Candidate candidate,
        Model model
) {
    // Tìm candidate trong cơ sở dữ liệu
    Candidate existingCandidate = candidateServices.findCandidateById(id);

    // Lưu Address trước khi lưu Candidate nếu chưa có trong CSDL
    if (candidate.getAddress() != null) {
        // Lưu Address vào CSDL
        addressRepository.save(candidate.getAddress());
    }

    // Cập nhật thông tin của Candidate từ form
    existingCandidate.setDob(candidate.getDob());
    existingCandidate.setEmail(candidate.getEmail());
    existingCandidate.setFullName(candidate.getFullName());
    existingCandidate.setPhone(candidate.getPhone());
    existingCandidate.setAddress(candidate.getAddress());

    // Cập nhật mã quốc gia từ countryCode (sử dụng CountryCode để lấy tên quốc gia)
    if (candidate.getAddress() != null && candidate.getAddress().getCountry() != null) {
        // Chuyển mã quốc gia từ Short thành CountryCode
        Short countryShort = candidate.getAddress().getCountry();
        CountryCode countryCode = CountryCode.getByCode(countryShort);

        if (countryCode != null) {
            // Lưu quốc gia vào Address
            candidate.getAddress().setCountry(countryShort);
        }
    }

    // Lưu Candidate đã chỉnh sửa vào CSDL
    candidateServices.saveCandidate(existingCandidate);

    model.addAttribute("candidates", candidateRepository.findAll());
    return "candidates/candidates"; // Chuyển hướng về danh sách Candidate
}

    @GetMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable("id") Long id, Model model) {
        // Xóa candidate theo id
        candidateServices.deleteCandidateById(id);

        // Cập nhật danh sách candidates và trả về view
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates"; // Điều hướng về trang danh sách candidates
    }
    @Autowired
    private CompanyServices companyServices;
    @Autowired
    private JobService jobService;
    @Autowired
    private SkillService skillService;

@PostMapping("/addSkillForCandidate")
public String addNewSkillForCandidate(Model model, @RequestParam("newSkill") String newSkill,
                                      @RequestParam("idCandidate") Long idCandidate,
                                      @RequestParam("skillDesc") String skillDesc,
                                      @RequestParam("skillType") Byte skillType,
                                      @RequestParam("skillMoreInfo") String skillMoreInfo,
                                      @RequestParam("skillLevel") Byte skillLevel) {
    // Lấy thông tin ứng viên
    Candidate candidate = candidateServices.findCandidateById(idCandidate);

    // Kiểm tra và thêm kỹ năng nếu chưa tồn tại
    Skill skill = skillService.findByName(newSkill);
    if (skill == null) {
        skill = new Skill();
        skill.setSkillName(newSkill);
        skill.setSkillDescription(skillDesc);
        skill.setType(skillType);
        skill = skillRepository.save(skill);
    }
        CandidateSkillId candidateSkillId = new CandidateSkillId();
        candidateSkillId.setCanId(candidate.getId());
        candidateSkillId.setSkillId(skill.getId());


    // Thêm kỹ năng vào ứng viên
    CandidateSkill candidateSkill = new CandidateSkill();
    candidateSkill.setId(candidateSkillId);
    candidateSkill.setSkill(skill);
    candidateSkill.setSkillLevel(skillLevel);
    candidateSkill.setMoreInfos(skillMoreInfo);
    candidateSkill.setCan(candidate);
    candidateSkillService.addCandidateSkill(candidateSkill);

    // Đảm bảo kỹ năng được liên kết với công việc phù hợp
    List<Job> jobsRequiringSkill = jobService.findJobsBySkillId(skill.getId());
    if (jobsRequiringSkill.isEmpty()) {
        // Logic để thêm liên kết skill-job vào job_skill (nếu cần)
        jobService.addSkillToRelevantJobs(skill);
    }

    // Hiển thị thông tin ứng viên với các công việc phù hợp
    List<CandidateSkill> candidateSkills = candidateSkillService.getSkillsByCandidate(candidate.getId());
    model.addAttribute("CandidateSkill", candidateSkills);
    model.addAttribute("candidate", candidate);
    model.addAttribute("company", companyServices.findAll());
    model.addAttribute("job", jobService.getMatchingJobsForCandidate(candidate.getId()));
    model.addAttribute("UserName", candidate.getFullName());
    model.addAttribute("Skills", skillService.getAllSkills());

    return "candidates/home-candidate";
}




}
