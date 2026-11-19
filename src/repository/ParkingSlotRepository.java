package repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import domain.ParkingSlot;
import domain.Vehicle;
import domain.Vehicle.VehicleType;

public class ParkingSlotRepository {

	private Map<UUID, ParkingSlot> parkignSlots = new ConcurrentHashMap<>();

	public Optional<ParkingSlot> allocateSlot(Vehicle vehicle) {
		return parkignSlots.values().stream()
				.filter(ps -> ps.getSlotType() == vehicle.getVehicleType() && !ps.isOccupied()).findFirst().map(ps -> {
					ps.setOccupied(true);
					ps.setVehicleId(vehicle.getId());
					return ps;
				});
	}

	public ParkingSlot save(ParkingSlot parkingSlot) {
		parkignSlots.put(parkingSlot.getId(), parkingSlot);
		return parkingSlot;
	}

	public Map<VehicleType, Long> getSlotStatistics() {
		return parkignSlots.values().stream()
				.collect(Collectors.groupingBy(ParkingSlot::getSlotType, Collectors.counting()));
	}

	public void releaseSlot(UUID slotId) {
		ParkingSlot slot = parkignSlots.get(slotId);
		if (slot != null) {
			slot.setOccupied(false);
			parkignSlots.put(slotId, slot);
		}
	}
}