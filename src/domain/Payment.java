package domain;

import java.util.UUID;

public class Payment {
	
	UUID id;
	
	UUID ticketId;
	
	double amount;
		
	private PaymentGateway gateway;
	
	private PaymentStatus paymentStatus;
	
    public Payment(UUID ticketId, double amount, PaymentGateway gateway) {
        this.id = UUID.randomUUID();
        this.ticketId = ticketId;
        this.amount = amount;
        this.gateway = gateway;
        this.paymentStatus = PaymentStatus.PENDING;
    }
	
	public enum PaymentGateway {
        RAZORPAY, STRIPE
    }
	
	public enum PaymentStatus {
        PENDING, SUCCESS, FAILED
    }
	
	public void markAsSuccess() {
		this.paymentStatus = PaymentStatus.SUCCESS;
	}

	public void markAsFailed() {
		this.paymentStatus = PaymentStatus.FAILED;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getTicketId() {
		return ticketId;
	}

	public void setTicketId(UUID ticketId) {
		this.ticketId = ticketId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentGateway getGateway() {
		return gateway;
	}

	public void setGateway(PaymentGateway gateway) {
		this.gateway = gateway;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", ticketId=" + ticketId + ", amount=" + amount + ", gateway=" + gateway
				+ ", paymentStatus=" + paymentStatus + "]";
	}
	
}
