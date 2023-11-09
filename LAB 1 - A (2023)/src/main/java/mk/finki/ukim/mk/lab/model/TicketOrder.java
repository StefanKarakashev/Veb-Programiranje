package mk.finki.ukim.mk.lab.model;


import lombok.Getter;

@Getter
public class TicketOrder {
    private String movieTitle;
    private String clientName;
    private String cleintAdress;
    private Long numberOfTickets;

    public TicketOrder(String movieTitle, String clientName, String clientAddress, Long numberOfTickets) {
        this.movieTitle = movieTitle;
        this.clientName = clientName;
        this.cleintAdress = clientAddress;
        this.numberOfTickets = numberOfTickets;
    }
}
