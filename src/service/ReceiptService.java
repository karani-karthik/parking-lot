package service;

import domain.Receipt;
import domain.Ticket;

public class ReceiptService {

	public Receipt generateReceipt(Ticket ticket, double fee) {
		System.out.println("Generating receipt for ticket: " + ticket.getId());

		Receipt receipt = new Receipt(ticket.getId(), fee);
		
		System.out.println("Receipt generated: " + receipt.getId() + " with fee: " + fee);
		
		return receipt;
	}

	public void markReceiptAsPaid(Receipt receipt) {
		System.out.println("Marking receipt as paid: " + receipt.getId());
        receipt.markAsPaid();
        System.out.println("Receipt marked as paid successfully");
	}

}
