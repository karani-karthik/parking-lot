package domain;

import java.util.UUID;

import domain.Vehicle.VehicleType;

public class ParkingSlot {

	private UUID id;

	private VehicleType slotType;

	private boolean isOccupied;

	private int floorNumber;

	private UUID vehicleId;

	public ParkingSlot(Vehicle.VehicleType slotType, int floorNumber) {
		this.id = UUID.randomUUID();
		this.slotType = slotType;
		this.isOccupied = false;
		this.floorNumber = floorNumber;
	}

	public ParkingSlot(Vehicle.VehicleType slotType, int floorNumber, UUID vehicleId) {
		this.id = UUID.randomUUID();
		this.slotType = slotType;
		this.isOccupied = false;
		this.floorNumber = floorNumber;
		this.vehicleId = vehicleId;
	}

	public UUID getVehicleId() {
		return vehicleId;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public VehicleType getSlotType() {
		return slotType;
	}

	public void setSlotType(VehicleType slotType) {
		this.slotType = slotType;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public void setVehicleId(UUID vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Override
	public String toString() {
		return "ParkingSlot [id=" + id + ", slotType=" + slotType + ", isOccupied=" + isOccupied + ", floorNumber="
				+ floorNumber + "]";
	}

}
