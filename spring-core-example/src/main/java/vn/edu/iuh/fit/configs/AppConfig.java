package vn.edu.iuh.fit.configs;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("vn.edu.iuh.fit")
/*@ComponentScans(
        {@ComponentScan("vn.edu.iuh.fit.java_based"),
        @ComponentScan("vn.edu.iuh.fit.xml_based")}
)*/
public class AppConfig {
    //no-op
}
