package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public CourseServiceImplementation(CourseRepository courseRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Student student = studentService.findByUsername(username);
        Course course = courseRepository.findByID(courseId);
        courseRepository.addStudentToCourse(student, course);

        return course;
    }

    @Override
    public Course getCourseByID(Long courseID) {
        return courseRepository.findByID(courseID);
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAllCourses();
    }
}
