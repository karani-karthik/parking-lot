package domain;

import java.util.UUID;

public class Vehicle {

	UUID id;

	String licensePlate;

	VehicleType vehicleType;

	public enum VehicleType {
		CAR, BIKE, EV, TRUCK
	}

	public Vehicle(String licensePlate, VehicleType vehicleType) {
		this.id = UUID.randomUUID();
		this.licensePlate = licensePlate;
		this.vehicleType = vehicleType;
	}

	public UUID getId() {
		return id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

}
