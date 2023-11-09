package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Course {
    private Long courseID;
    private String name;
    private String description;
    private List<Student> students;

    public Course(Long courseID, String name, String description) {
        this.courseID = courseID;
        this.name = name;
        this.description = description;
        students = new ArrayList<>();
    }

}
