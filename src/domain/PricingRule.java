package domain;

import java.util.UUID;

import domain.Vehicle.VehicleType;

public class PricingRule {

	private UUID id;

	private VehicleType vehicleType;

	private double ratePerHour;

	private double flatRate;

	public PricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {
		this.id = UUID.randomUUID();
		this.vehicleType = vehicleType;
		this.ratePerHour = ratePerHour;
		this.flatRate = flatRate;
	}

	public void updateRates(double ratePerHour, double flatRate) {
		this.ratePerHour = ratePerHour;
		this.flatRate = flatRate;
	}

	public void updateFlatRate(double flatRate) {
		this.flatRate = flatRate;
	}

	public void updateHourlyRate(double ratePerHour) {
		this.ratePerHour = ratePerHour;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public double getRatePerHour() {
		return ratePerHour;
	}

	public void setRatePerHour(double ratePerHour) {
		this.ratePerHour = ratePerHour;
	}

	public double getFlatRate() {
		return flatRate;
	}

	public void setFlatRate(double flatRate) {
		this.flatRate = flatRate;
	}

	@Override
	public String toString() {
		return "PricingRule [id=" + id + ", vehicleType=" + vehicleType + ", ratePerHour=" + ratePerHour + ", flatRate="
				+ flatRate + "]";
	}

}
