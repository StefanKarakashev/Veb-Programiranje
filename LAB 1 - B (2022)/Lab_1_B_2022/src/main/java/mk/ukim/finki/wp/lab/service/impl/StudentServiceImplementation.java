package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        if (text == null || text.isEmpty())
            throw new IllegalArgumentException("Text is invalid!");

        return studentRepository.findAllStudents();
    }

    @Override
    public Student findByUsername(String username) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Username is invalid!");

        return studentRepository.findByUsername(username);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        Student student = new Student(username, password, name, surname);
        studentRepository.save(student);

        return student;
    }


}
