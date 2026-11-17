package service;

import java.util.Optional;

import domain.ParkingSlot;
import domain.Vehicle;
import repository.ParkingSlotRepository;

public class SlotService {

	private ParkingSlotRepository slotRepository;

	public SlotService(ParkingSlotRepository slotRepository) {
		this.slotRepository = slotRepository;
	}

	public Optional<ParkingSlot> allocateSlot(Vehicle vehicle) {
		Optional<ParkingSlot> slot = slotRepository.allocateSlot(vehicle);

		if (slot.isPresent()) {
			System.out.println("Slot allocated successfully: " + slot.get().getId());
		} else {
			System.out.println("No available slots for vehicle type: " + vehicle.getVehicleType());
		}

		return slot;
	}

}
