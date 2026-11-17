package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import domain.Floor;

public class FloorRepository {
	private Map<UUID, Floor> floors = new ConcurrentHashMap<>();
	private Map<Integer, UUID> floorNumberToId = new ConcurrentHashMap<>();

	public boolean existsByFloorNumber(int floorNumber) {
		return floorNumberToId.containsKey(floorNumber);
	}
	
	public Floor findByFloorNumber(int floorNumber) {
		UUID floorId = floorNumberToId.get(floorNumber);
		
		if(floorId == null) {
			return null;
		}
		
		return floors.get(floorId);
	}

	public Floor save(Floor floor) {
		floors.put(floor.getId(), floor);
		floorNumberToId.put(floor.getFloorNumber(), floor.getId());
		
		return floor;
	}

	public List<Floor> findAll() {
		return new ArrayList<>(floors.values());
	}

}
