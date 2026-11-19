package domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Receipt {

	UUID id;

	UUID ticketId;

	LocalDateTime exitTime;

	double totalFee;

	private PaymentStatus paymentStatus;

	public enum PaymentStatus {
		PENDING, SUCCESS, FAILED
	}

	public Receipt(UUID ticketId, double totalFee) {
		this.id = UUID.randomUUID();
		this.ticketId = ticketId;
		this.exitTime = LocalDateTime.now();
		this.totalFee = totalFee;
		this.paymentStatus = PaymentStatus.PENDING;
	}

	public void markAsPaid() {
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

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Receipt [id=" + id + ", ticketId=" + ticketId + ", exitTime=" + exitTime + ", totalFee=" + totalFee
				+ ", paymentStatus=" + paymentStatus + "]";
	}
	
}

