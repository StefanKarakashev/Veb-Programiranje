package mk.ukim.finki.wp.lab.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = "/summary")
public class StudentEnrollmentSummary extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final StudentService studentService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("studentUsername");
        Long courseID = (Long) req.getSession().getAttribute("courseID");
        Course course =  courseService.addStudentInCourse(userName, courseID);


        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);


        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("courseSummary", course);

        springTemplateEngine.process(
                "studentsInCourse.html",
                webContext,
                resp.getWriter()
        );

    }
}
