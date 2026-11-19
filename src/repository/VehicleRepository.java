package repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import domain.Vehicle;

public class VehicleRepository {
	private Map<UUID, Vehicle> vehicles = new ConcurrentHashMap<>();
	
	public Vehicle save(Vehicle vehicle) {
		vehicles.put(vehicle.getId(), vehicle);
		return vehicle;
	}
	
	public Vehicle findById(UUID vehicle) {
		return vehicles.get(vehicle);
	}
}
