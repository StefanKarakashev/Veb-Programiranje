package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(urlPatterns = "/movie-list")
public class MovieListServlet extends HttpServlet {
    private final MovieService movieService;
    private SpringTemplateEngine springTemplateEngine;

    public MovieListServlet(MovieService movieService, SpringTemplateEngine springTemplateEngine) {
        this.movieService = movieService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("movies", movieService.listAll());


        String searchText = req.getParameter("text");
        String movieRating = req.getParameter("rating");

        if (searchText != null && movieRating != null) {
            List<Movie> searchedMoviesByText = movieService.searchMovies(searchText);
            List<Movie> searchedMoviesByRating = movieService
                    .listAll().stream()
                    .filter(m ->  m.getRating() == Integer.parseInt(movieRating))
                    .toList();
            context.setVariable("searchMovies",
                    Stream.of(searchedMoviesByText, searchedMoviesByRating)
                            .flatMap(List::stream)
                            .distinct()
                            .collect(Collectors.toList()));
        }

        springTemplateEngine.process(
                "listMovies.html",
                context,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieName = req.getParameter("selectedMovie");
        String numTickets = req.getParameter("numTickets");

        String textSearch = req.getParameter("textSearch");
        String movieRating = req.getParameter("movieRating");

        if (textSearch != null && movieRating != null) {
            String redirectURL = "/movie-list?text=" + URLEncoder.encode(textSearch, "UTF-8") + "&rating=" + URLEncoder.encode(movieRating, "UTF-8");
            resp.sendRedirect(redirectURL);
        }

        if (movieName != null && numTickets != null) {
            String redirectURL = "/ticketOrder?movieName=" + URLEncoder.encode(movieName, "UTF-8") + "&numTickets=" + URLEncoder.encode(numTickets, "UTF-8");
            resp.sendRedirect(redirectURL);
        }
    }
}
