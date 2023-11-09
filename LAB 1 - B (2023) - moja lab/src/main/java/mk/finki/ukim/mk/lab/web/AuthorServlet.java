package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(urlPatterns = "/author")
public class AuthorServlet extends HttpServlet {

    private final AuthorService authorService;
    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public AuthorServlet(AuthorService authorService, BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        Book book = (Book) req.getSession().getAttribute("book");

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("bookForAuthor", book);
        webContext.setVariable("authorList", authorService.listAuthors());

        springTemplateEngine.process(
                "authorList.html",
                webContext,
                resp.getWriter()
        );

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookISBN = req.getParameter("selectedBook");
        Book book = bookService.findBookByIsbn(bookISBN);
        req.getSession().setAttribute("book", book);
        resp.sendRedirect("/author");
    }
}
