package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(urlPatterns = "/ticketOrder")
public class TicketOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final TicketOrderService ticketOrderService;

    public TicketOrderServlet(SpringTemplateEngine springTemplateEngine, TicketOrderService ticketOrderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.ticketOrderService = ticketOrderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("movieName");

        TicketOrder order = ticketOrderService.placeOrder(req.getParameter("movieName"), req.getRemoteHost(), req.getRemoteAddr(), 4);
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("movieName", order.getMovieTitle());
        context.setVariable("address", order.getCleintAdress());
        context.setVariable("numTickets", order.getNumberOfTickets());
        context.setVariable("clientName", order.getClientName());
        springTemplateEngine.process(
                "orderConfirmation.html",
                context,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
