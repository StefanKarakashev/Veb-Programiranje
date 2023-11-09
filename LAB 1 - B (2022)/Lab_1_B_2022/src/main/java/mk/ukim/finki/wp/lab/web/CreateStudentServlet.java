package mk.ukim.finki.wp.lab.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = "/StudentEnrollmentSummary")
public class CreateStudentServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;

    public CreateStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        springTemplateEngine.process(
                "createStudent.html",
                context,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        studentService.save(userName, password, name, surname);
        resp.sendRedirect("/addStudent");
    }
}
