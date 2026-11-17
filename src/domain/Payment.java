package domain;

import java.util.UUID;

public class Payment {
	
	UUID id;
	
	UUID ticketId;
	
	double amount;
		
	private PaymentGateway gateway;
	
	private PaymentStatus paymentStatus;
	
	public enum PaymentGateway {
        RAZORPAY, STRIPE
    }
	
	public enum PaymentStatus {
        PENDING, SUCCESS, FAILED
    }
}
