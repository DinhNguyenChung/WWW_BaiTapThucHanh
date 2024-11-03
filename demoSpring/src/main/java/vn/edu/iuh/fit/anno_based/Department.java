package vn.edu.iuh.fit.anno_based;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Department {
    private String name;
    private Company company;

    public Department() {
    }

    public Department(String name, Company company) {
        this.name = name;
        this.company = company;
    }
//    @Autowired
    public Department( Company company) {
        this.name = "no-name";
        this.company = company;
    }

//    public String getName() {
//        return name;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", company=" + company +
                '}';
    }
}
