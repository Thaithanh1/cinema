package movie.service;

import movie.entity.Ticket;
import movie.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public void editTicket(int ticketId) {
        Ticket ticket = ticketRepository.findByTicketID(ticketId);
        ticket.setActive(false);
        ticketRepository.save(ticket);
    }
}
