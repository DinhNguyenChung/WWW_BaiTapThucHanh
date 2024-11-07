package vn.edu.iuh.fit.lab_week5_spr_mvc;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.converters.CountryCodeMapper;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Address;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Candidate;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.CandidateRepository;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class LabWeek5SprMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabWeek5SprMvcApplication.class, args);
    }
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AddressRepository addressRepository;
//    @Bean
//    CommandLineRunner initData() {
//        return args -> {
//            Random rnd = new Random();
//            for (int i = 1; i < 1000; i++) {
//                // Tạo đối tượng Address theo constructor yêu cầu
//                Address add = new Address(
//                        "Quang Trung",
//                        "HCM",
//                        CountryCodeMapper.mapToShort(CountryCode.VN) ,  // country (cần ép kiểu nếu CountryCode không là Short)
//                        rnd.nextInt(1, 1000) + ""  // number
//                );
//                addressRepository.save(add);
//
//                // Tạo đối tượng Candidate theo constructor yêu cầu
//                Candidate can = new Candidate(
//                        LocalDate.of(1998, rnd.nextInt(1, 13), rnd.nextInt(1, 29)),  // dob
//                        "email_" + i + "@gmail.com",  // email
//                        "Name #" + i,  // fullName
//                        rnd.nextLong(1111111111L, 9999999999L) + "",  // phone
//                        add  // address
//                );
//                candidateRepository.save(can);
//                System.out.println("Added: " + can);
//            }
//        };
//    }

}
