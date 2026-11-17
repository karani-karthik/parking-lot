package service;

import java.util.UUID;

import domain.Ticket;
import domain.Vehicle;
import repository.TicketRepository;

public class TicketService {

	private TicketRepository ticketRepository;

	public TicketService(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	public Ticket generateTicket(Vehicle vehicle, UUID slotId) {
		Ticket ticket = new Ticket(vehicle.getId(), slotId);
		ticketRepository.save(ticket);

		System.out.println("Ticket generated successfully: " + ticket.getId());
		return ticket;
	}
}
