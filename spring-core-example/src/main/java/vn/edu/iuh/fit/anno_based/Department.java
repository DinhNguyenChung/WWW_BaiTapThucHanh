package vn.edu.iuh.fit.anno_based;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Department {
    private String name;
//    @Autowired
    private Company company;

    public Department() {
    }

//    @Autowired   //automatically known
    public Department(Company company) {
        this.name = "no-name";
        this.company = company;
    }

    public String getName() {
        return name;
    }

//    @Autowired
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
