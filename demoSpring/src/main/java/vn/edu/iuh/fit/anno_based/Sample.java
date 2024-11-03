package vn.edu.iuh.fit.anno_based;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vn.edu.iuh.fit.config.AppConfig;

public class Sample {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Department dept = context.getBean(Department.class);

        System.out.println(dept);
        Company company = context.getBean(Company.class);
        System.out.println(company);
        company= new Company("SoftWare");
        dept = new Department("FPT",company);
        System.out.println(dept);
    }
}
