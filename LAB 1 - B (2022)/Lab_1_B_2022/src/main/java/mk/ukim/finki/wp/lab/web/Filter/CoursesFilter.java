package mk.ukim.finki.wp.lab.web.Filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Course;

import java.io.IOException;


@WebFilter(filterName = "auth-filter", urlPatterns = "/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        initParams = @WebInitParam(name = "ignore-path", value = "/listCourses")
)

public class CoursesFilter implements Filter {

    private String ignorePath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ignorePath = filterConfig.getInitParameter("ignore-path");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Course sessionCourse = (Course) request.getSession().getAttribute("course");
        String course = (String) request.getParameter("course");

        String path = request.getServletPath();


       if (ignorePath.equals(path)) {
           filterChain.doFilter(servletRequest, servletResponse);
           return;
       }

       if (course == null && sessionCourse == null)
           response.sendRedirect("/listCourses");
       else
           filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


}

