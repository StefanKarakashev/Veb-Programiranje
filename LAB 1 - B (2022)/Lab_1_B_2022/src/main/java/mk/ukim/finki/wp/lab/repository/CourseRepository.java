package mk.ukim.finki.wp.lab.repository;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CourseRepository {
    private List<Course> courseList;

    @PostConstruct
    public void init() {
        courseList = new ArrayList<>();
        for (int i = 1; i <= 5; i++)
            courseList.add(new Course((long)i, "course" + i, "course_descipriton" + i));
    }

    public List<Course> findAllCourses() {
        return courseList;
    }

    public Course findByID(Long courseID) {
        return courseList.stream()
                .filter(c -> c.getCourseID().equals(courseID))
                .findFirst()
                .orElse(null);
    }

    public List<Student> findAllStudentsByCourse(Long courseID) {
        return courseList.stream()
                .filter(c -> c.getCourseID().equals(courseID))
                .findFirst()
                .map(Course::getStudents)
                .orElse(Collections.emptyList());

    }

    public Course addStudentToCourse(Student student, Course course) {
        course.getStudents().add(student);
        return course;
    }


}
