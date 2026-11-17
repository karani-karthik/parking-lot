package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import domain.Vehicle.VehicleType;

public class Floor {

	private UUID id;
	
    private int floorNumber;

    private Map<VehicleType, List<ParkingSlot>> slotsByTpe;

    public Floor(int floorNumber) {
        this.id = UUID.randomUUID();
        this.floorNumber = floorNumber;
        slotsByTpe = new EnumMap<>(VehicleType.class);
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Map<VehicleType, List<ParkingSlot>> getSlotsByTpe() {
		return slotsByTpe;
	}

	public void setSlotsByTpe(Map<VehicleType, List<ParkingSlot>> slotsByTpe) {
		this.slotsByTpe = slotsByTpe;
	}

	@Override
	public String toString() {
		return "Floor [id=" + id + ", floorNumber=" + floorNumber + ", slotsByTpe=" + slotsByTpe + "]";
	}
    
}
