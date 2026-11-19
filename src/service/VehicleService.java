package service;

import java.util.UUID;

import domain.Vehicle;
import repository.VehicleRepository;

public class VehicleService {

	private final VehicleRepository vehicleRepository;

	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	public Vehicle saveVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	public Vehicle getVehicleById(UUID vehicleId) {
		return vehicleRepository.findById(vehicleId);
	}

}
