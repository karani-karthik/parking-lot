package repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import domain.Ticket;

public class TicketRepository {
	private Map<UUID, Ticket> tickets = new ConcurrentHashMap<>();

	public Ticket save(Ticket ticket) {
		tickets.put(ticket.getId(), ticket);
		return ticket;
	}

	public Ticket findById(UUID ticketId) {
		return tickets.get(ticketId);
	}

	public void deactivateTicket(UUID ticketId) {
		Ticket ticket = tickets.get(ticketId);

		if (ticket != null) {
			ticket.deactivate();
			tickets.put(ticketId, ticket);
		}
	}
}
