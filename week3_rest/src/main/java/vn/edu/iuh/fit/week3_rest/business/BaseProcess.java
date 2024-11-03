package vn.edu.iuh.fit.week3_rest.business;

import vn.edu.iuh.fit.week3_rest.modals.Student;

import java.util.ArrayList;
import java.util.List;

public class BaseProcess {
    public final List<Student> students = new ArrayList<Student>();

    public List<Student> getStudents() {
        //....
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student(1,"An","an@gmail.com"));
        list.add(new Student(2,"Binh","binh@gmail.com"));
        list.add(new Student(3,"Duong","duong@gmail.com"));
        return list;
    }
    public Student getStudentById(long id) {
        //..
        return null;
    }
}
