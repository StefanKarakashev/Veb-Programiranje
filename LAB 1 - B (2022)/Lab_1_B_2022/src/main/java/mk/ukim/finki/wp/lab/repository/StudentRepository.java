package mk.ukim.finki.wp.lab.repository;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    private List<Student> students;

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        for (int i = 1; i <= 5; i++)
            students.add(new Student("student_" + i, "pass" + i, "student_name" + i,"student_surname" + i));
    }

    public List<Student> findAllStudents() {
        return students;
    }


    public List<Student> findAllStudents(String text) {
        return students.stream()
                .filter(s -> s.getName().contains(text) || s.getSurname().contains(text))
                .collect(Collectors.toList());
    }

    public Student findByUsername(String text) {
        return students.stream()
                .filter(s -> s.getUsername().equals(text))
                .findFirst()
                .orElse(null);
    }

    public void save(Student student) {
        students.add(student);
    }
}
