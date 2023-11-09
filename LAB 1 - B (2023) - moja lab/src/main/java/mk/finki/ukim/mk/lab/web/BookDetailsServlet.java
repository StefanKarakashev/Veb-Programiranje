package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(urlPatterns = "/bookDetails")
public class BookDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    public BookDetailsServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);


        WebContext context = new WebContext(webExchange);
        Book book = (Book) req.getSession().getAttribute("book");
        Author author = (Author) req.getSession().getAttribute("addedAuthor");

        context.setVariable("book", book);
        context.setVariable("author", author);

        springTemplateEngine.process(
                "bookDetails.html",
                context,
                resp.getWriter()
        );

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long authorID = Long.valueOf(req.getParameter("authorToBeAdded"));
        Book book = (Book) req.getSession().getAttribute("book");
        Author author = bookService.addAuthorToBook(authorID, book.getIsbn());
        req.getSession().setAttribute("addedAuthor", author);
        resp.sendRedirect("/bookDetails");
    }
}
