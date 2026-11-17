package adapter;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class StripeAdapter implements PaymentGatewayAdapter {

	private static final double SUCCESS_PROBABILITY = 0.95;

	@Override
	public boolean pay(UUID ticketId, double amount) {
		if (ticketId == null) {
			throw new IllegalArgumentException("Ticket ID is required to process payment.");
		}

		if (!Double.isFinite(amount) || amount <= 0.0) {
			throw new IllegalArgumentException(
					"Payment amount must be a finite number greater than 0. Received " + amount + ".");
		}

		boolean success = ThreadLocalRandom.current().nextDouble() < SUCCESS_PROBABILITY;

		System.out.printf("[Stripe simulation] Payment %s for ticket %s. Amount %s.%n",
				success ? "succeeded" : "failed", ticketId, amount);

		return success;
	}

}
