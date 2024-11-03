package vn.edu.iuh.fit.xml_based;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    /*@BeforeAll
    static void doAll(){

    }*/

    private ApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new ClassPathXmlApplicationContext("teo_beans.xml");
    }

    @Test
    void testExdist() {
        Student student = (Student) context.getBean("s1");
        assertNotNull(student);
    }
    @Test
    void getId() {
        Student student = (Student) context.getBean("s1");
        assertEquals(student.getId(), 120);
    }

    @Test
    void getName() {
        Student student = (Student) context.getBean("s1");
        assertEquals(student.getName(), "Than Thi det");
    }
}