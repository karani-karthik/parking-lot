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
	
}
