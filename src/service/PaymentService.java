package service;

import java.util.UUID;

import adapter.PaymentGatewayAdapter;
import adapter.RazorpayAdapter;
import adapter.StripeAdapter;
import domain.Payment;
import domain.Payment.PaymentGateway;
import repository.PaymentRepository;

public class PaymentService {

	private final PaymentRepository paymentRepository;
	private PaymentGatewayAdapter defaultGateway;

	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
		this.defaultGateway = new RazorpayAdapter();
	}

	public boolean processPaymentWithRetry(UUID ticketId, double amount, int maxRetries) {
		 System.out.println("Processing payment with retry for ticket: " + ticketId);
		 
		for (int attempt = 1; attempt <= maxRetries; attempt++) {
			System.out.println("Payment attempt " + attempt + " of " + maxRetries);

			boolean success = processPayment(ticketId, amount);

			if (success) {
				System.out.println("Payment successful on attempt " + attempt);
				return true;
			}

			if (attempt > 1) {
				defaultGateway = new StripeAdapter();
				System.out.println("Switching to Stripe gateway for retry");
			}
		}

		System.out.println("Payment failed after " + maxRetries + " attempts");
		return false;
	}

	public boolean processPayment(UUID ticketId, double amount) {
		Payment payment = new Payment(ticketId, amount, PaymentGateway.RAZORPAY);
		paymentRepository.save(payment);
		
		System.out.println("Processing payment for ticket: " + ticketId + " amount: " + amount);

		boolean success = defaultGateway.pay(ticketId, amount);

		if (success) {
			payment.markAsSuccess();
		} else {
			payment.markAsFailed();
		}

		System.out.println("Payment processed with status: " + (success ? "SUCCESS" : "FAILED"));

		paymentRepository.update(payment);
		return success;
	}

    public void setDefaultGateway(PaymentGatewayAdapter gateway) {
        this.defaultGateway = gateway;
    }
}
