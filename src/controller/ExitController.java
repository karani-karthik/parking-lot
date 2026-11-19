package controller;

import java.util.UUID;

import domain.Receipt;
import domain.Ticket;
import service.PaymentService;
import service.PricingService;
import service.ReceiptService;
import service.SlotService;
import service.TicketService;

public class ExitController {

	private final TicketService ticketService;
	private final PricingService pricingService;
	private final PaymentService paymentService;
	private final ReceiptService receiptService;
	private final SlotService slotService;

	public ExitController(TicketService ticketService, PricingService pricingService, PaymentService paymentService,
			ReceiptService receiptService, SlotService slotService) {
		this.ticketService = ticketService;
		this.pricingService = pricingService;
		this.paymentService = paymentService;
		this.receiptService = receiptService;
		this.slotService = slotService;
	}

	public ExitResult exitVehicle(UUID ticketId) {
		Ticket ticket = ticketService.getTicketById(ticketId);

		if (ticket == null) {
			return new ExitResult(false, null, 0.0, "Ticket not found");
		}

		if (!ticket.isActive()) {
			return new ExitResult(false, null, 0.0, "Ticket is not active");
		}

		double fee = pricingService.calculateFee(ticket);

		boolean paymentSuccess = paymentService.processPaymentWithRetry(ticketId, fee, 3);
		if (!paymentSuccess) {
			return new ExitResult(false, null, fee, "Payment failed");
		}

		Receipt receipt = receiptService.generateReceipt(ticket, fee);
		
		receiptService.markReceiptAsPaid(receipt);

		slotService.releaseSlot(ticket.getSlotId());

		ticketService.deactivateTicket(ticketId);
		
		return new ExitResult(true, receipt.getId(), fee, "Exit successful");
	}

	public static class ExitResult {
		private final boolean success;
		private final UUID receiptId;
		private final double fee;
		private final String message;

		public ExitResult(boolean success, UUID receiptId, double fee, String message) {
			this.success = success;
			this.receiptId = receiptId;
			this.fee = fee;
			this.message = message;
		}

		public boolean isSuccess() {
			return success;
		}

		public UUID getReceiptId() {
			return receiptId;
		}

		public double getFee() {
			return fee;
		}

		public String getMessage() {
			return message;
		}
	}
}
