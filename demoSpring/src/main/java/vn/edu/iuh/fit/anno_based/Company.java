package vn.edu.iuh.fit.anno_based;

import org.springframework.stereotype.Component;

@Component
public class Company {
    private String name;

    public Company() {
    }
    public Company(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
