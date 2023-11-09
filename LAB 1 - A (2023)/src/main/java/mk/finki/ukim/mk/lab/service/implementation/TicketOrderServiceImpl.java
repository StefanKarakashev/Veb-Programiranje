package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    private void checkString(String str) {
        if (str.isEmpty())
            throw new IllegalArgumentException("The string is empty!");
    }

    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        checkString(movieTitle);
        checkString(clientName);
        checkString(address);

        if (numberOfTickets <= 0)
            throw new IllegalArgumentException("Number of tickets cant be 0!");

        return new TicketOrder(movieTitle, clientName, address, (long) numberOfTickets);
    }
}
