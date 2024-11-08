package vn.edu.iuh.fit.lab_week5_spr_mvc.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services.CompanyServices;

@Controller
@RequestMapping("/company")
public class CompanyController
{
    @Autowired
    private CompanyServices companyServices;
    @GetMapping("/companies")
    public String companies(Model model){
        model.addAttribute("company",companyServices.findAll());
        return "companies/companys";
    }
}
