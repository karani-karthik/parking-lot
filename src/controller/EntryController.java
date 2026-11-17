package controller;

import java.util.Optional;
import java.util.UUID;

import domain.ParkingSlot;
import domain.Ticket;
import domain.Vehicle;
import domain.Vehicle.VehicleType;
import service.SlotService;
import service.TicketService;

public class EntryController {

	private SlotService slotService;

	private TicketService ticketService;

	public EntryController(SlotService slotService, TicketService ticketService) {
		this.slotService = slotService;
		this.ticketService = ticketService;
	}

	public EntryResult enterVehicle(String licensePlate, VehicleType vehicleType) {

		if (licensePlate == null || licensePlate.isBlank()) {
			return new EntryResult(false, null, null, "License plate is required.");
		}

		if (vehicleType == null) {
			return new EntryResult(false, null, null, "Vehicle type is required.");
		}

		String plate = licensePlate.strip();
		Vehicle vehicle = new Vehicle(plate, vehicleType);
		System.out.println("Vehicle created: " + vehicle.getId());

		Optional<ParkingSlot> allocatedSlot = slotService.allocateSlot(vehicle);

		if (allocatedSlot.isEmpty()) {
			return new EntryResult(false, null, null,
					"No parking slots are available for vehicle type " + vehicleType + ".");
		}

		UUID slotId = allocatedSlot.get().getId();
		Ticket ticket = ticketService.generateTicket(vehicle, slotId);

		return new EntryResult(true, ticket.getId(), slotId, "Parking ticket issued for vehicle " + plate + ".");
	}

	public static class EntryResult {
		private final boolean success;
		private final UUID ticketId;
		private final UUID slotId;
		private final String message;

		public EntryResult(boolean success, UUID ticketId, UUID slotId, String message) {
			this.success = success;
			this.ticketId = ticketId;
			this.slotId = slotId;
			this.message = message;
		}

		public boolean isSuccess() {
			return success;
		}

		public UUID getTicketId() {
			return ticketId;
		}

		public UUID getSlotId() {
			return slotId;
		}

		public String getMessage() {
			return message;
		}
	}
}
