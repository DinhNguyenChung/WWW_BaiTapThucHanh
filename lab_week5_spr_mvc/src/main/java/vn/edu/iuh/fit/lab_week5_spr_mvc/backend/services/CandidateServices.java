package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.DTOs.CandidatesByJobDTO;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Candidate;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CandidateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServices {
    @Autowired
    private CandidateRepository candidateRepository;

    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy,
                                   String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);//findFirst.../findTop...
    }

    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
    public Candidate findCandidateById(Long id) {
        return candidateRepository.findById(Long.valueOf(id)).orElse(null);
    }
    public void deleteCandidateById(Long id){
        candidateRepository.deleteById(id);
    }
    public  Candidate findCandidateByEmail(String email){
        return candidateRepository.findCandidatesByEmail(email);
    }
    public List<CandidatesByJobDTO> getCandidatesByJobName(String jobName){
        List<Object[]> rs = candidateRepository.findCandidatesByJob(jobName);
        List<CandidatesByJobDTO> csds = new ArrayList<CandidatesByJobDTO>();
        for (Object[] row : rs) {
            Long id = (Long) row[0];
            String name = (String) row[1];
            String email = (String) row[2];
            String NameJob =(String) row[3];
            String DescJob =(String) row[4];
            String NameSkill = (String) row[5];
            String DescSkill =(String) row[6];
//            CandidatesByJobDTO dto = new CandidatesByJobDTO(id,name,email,NameJob,DescJob);
            CandidatesByJobDTO dto = new CandidatesByJobDTO(id, name, email, NameJob, DescJob, NameSkill, DescSkill);
            csds.add(dto);

        }
        return csds;
    }
}
