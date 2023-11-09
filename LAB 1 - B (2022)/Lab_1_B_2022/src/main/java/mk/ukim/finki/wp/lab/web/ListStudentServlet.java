package mk.ukim.finki.wp.lab.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final CourseService courseService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Course course = (Course) req.getSession().getAttribute("course");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("students", studentService.listAll());


        springTemplateEngine.process(
                "listStudents.html",
                webContext,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseID = req.getParameter("course");
        Course course = courseService.getCourseByID(Long.parseLong(courseID));
        req.getSession().setAttribute("course", course);
        req.getSession().setAttribute("courseID", course.getCourseID());

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("students", studentService.listAll());


        springTemplateEngine.process(
                "listStudents.html",
                webContext,
                resp.getWriter()
                );
    }
}
