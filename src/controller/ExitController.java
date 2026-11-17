package controller;

import java.util.UUID;

public class ExitController {

	public ExitResult exitVehicle(UUID ticketId) {
		// Retrieve ticket, calculate fee , process the payment, relase slot and
		// deactivate the ticket.
		return new ExitResult(true, null, 0, "Exit successful");
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
