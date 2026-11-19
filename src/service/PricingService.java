package service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import domain.PricingRule;
import domain.Ticket;
import domain.Vehicle;
import repository.PricingRuleRepository;

public class PricingService {

	private final PricingRuleRepository pricingRuleRepository;
	private final VehicleService vehicleService;

	public PricingService(PricingRuleRepository pricingRuleRepository, VehicleService vehicleService) {
		this.pricingRuleRepository = pricingRuleRepository;
		this.vehicleService = vehicleService;
	}

	public double calculateFee(Ticket ticket) {
		UUID vehicleId = ticket.getVehicleId();

		Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

		if (vehicle == null) {
			// exception
		}

		PricingRule pricingRule = pricingRuleRepository.findByVehicleType(vehicle.getVehicleType());

		if (pricingRule == null) {
			throw new IllegalStateException("No pricing rule found for vehicle type: " + vehicle.getVehicleType());
		}

		double flatFee = pricingRule.getFlatRate();
		double hourlyFee = calculateHourlyFee(ticket, pricingRule.getRatePerHour());

		double finalFee = Math.min(flatFee, hourlyFee);
		
		System.out.println("Flat fee: " + flatFee + ", Hourly fee: " + hourlyFee + ", Final fee: " + finalFee
				+ " for vehicle type: " + vehicle.getVehicleType());
		
		return finalFee;
	}

	private double calculateHourlyFee(Ticket ticket, double ratePerHour) {
		Duration duration = Duration.between(ticket.getEntryTime(), LocalDateTime.now());
		long hours = duration.toHours();

		if (hours < 1) {
			hours = 1;
		}

		return hours * ratePerHour;
	}

}
