package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.Floor;
import domain.ParkingSlot;
import domain.PricingRule;
import domain.Vehicle.VehicleType;
import repository.FloorRepository;
import repository.ParkingSlotRepository;
import repository.PricingRuleRepository;

public class AdminService {

	private final FloorRepository floorRepository;
	private final ParkingSlotRepository slotRepository;
	private final PricingRuleRepository ruleRepository;

	public AdminService(FloorRepository floorRepository, ParkingSlotRepository slotRepository,
			PricingRuleRepository ruleRepository) {
		this.floorRepository = floorRepository;
		this.slotRepository = slotRepository;
		this.ruleRepository = ruleRepository;
	}

	public void addFloor(int floorNumber) {

		validateFloorNumber(floorNumber);

		if (floorRepository.existsByFloorNumber(floorNumber)) {
			throw new IllegalStateException("Floor " + floorNumber + " already exists.");
		}

		Floor floor = new Floor(floorNumber);
		floorRepository.save(floor);
	}

	public void addSlotsToFloor(int floorNumber, VehicleType vehicleType, int count) {

		validateFloorNumber(floorNumber);

		validateVehicleType(vehicleType);

		if (count <= 0) {
			throw new IllegalArgumentException("Slot count must be at least 1. Received " + count + ".");
		}

		Floor floor = floorRepository.findByFloorNumber(floorNumber);

		if (floor == null) {
			throw new IllegalStateException("Floor " + floorNumber + " does not exist.");
		}

		for (int i = 0; i < count; i++) {
			ParkingSlot parkingSlot = new ParkingSlot(vehicleType, floorNumber);
			slotRepository.save(parkingSlot);
			floor.getSlotsByTpe().computeIfAbsent(vehicleType, type -> new ArrayList<>()).add(parkingSlot);
		}
	}

	public void addPricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {

		validateVehicleType(vehicleType);

		validateRate(ratePerHour, "Hourly rate");

		validateRate(flatRate, "Flat rate");

		if (ruleRepository.existsByVehicleType(vehicleType)) {
			throw new IllegalStateException("A pricing rule already exists for " + vehicleType + ".");
		}

		ruleRepository.save(new PricingRule(vehicleType, ratePerHour, flatRate));
	}

	public void updatePricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {
		
		validateVehicleType(vehicleType);
		
        validateRate(ratePerHour, "Hourly rate");
        
        validateRate(flatRate, "Flat rate");
        
		PricingRule rule = requirePricingRule(vehicleType);
		rule.updateRates(ratePerHour, flatRate);
		ruleRepository.update(rule);
	}

	public void updateFlatPricing(VehicleType vehicleType, double flatRate) {
		
		validateVehicleType(vehicleType);
		
        validateRate(flatRate, "Flat rate");

		PricingRule rule = requirePricingRule(vehicleType);
		rule.updateFlatRate(flatRate);
		ruleRepository.update(rule);
	}

	public void updateHourlyPricing(VehicleType vehicleType, double ratePerHour) {
		
		validateVehicleType(vehicleType);
        
		validateRate(ratePerHour, "Hourly rate");

        PricingRule rule = requirePricingRule(vehicleType);
		rule.updateHourlyRate(ratePerHour);
		ruleRepository.update(rule);
	}

	public Map<String, Object> getParkingStatus() {
		List<Floor> floors = floorRepository.findAll();
		Map<VehicleType, Long> slotStats = slotRepository.getSlotStatistics();

		return Map.of("totalFloors", floors.size(), "slotStatistics", slotStats);
	}
	
    private PricingRule requirePricingRule(VehicleType vehicleType) {
        PricingRule rule = ruleRepository.findByVehicleType(vehicleType);
        
        if (rule == null) {
            throw new IllegalStateException("No pricing rule exists for " + vehicleType + ".");
        }
        
        return rule;
    }

	private static void validateFloorNumber(int floorNumber) {
		if (floorNumber < 0) {
			throw new IllegalArgumentException("Floor number must be 0 or greater. Received " + floorNumber + ".");
		}
	}

	private static void validateVehicleType(VehicleType vehicleType) {
		if (vehicleType == null) {
			throw new IllegalArgumentException("Vehicle type is required.");
		}
	}

	private static void validateRate(double rate, String rateName) {
		if (!Double.isFinite(rate) || rate <= 0.0) {
			throw new IllegalArgumentException(
					rateName + " must be a finite number greater than 0. Received " + rate + ".");
		}
	}
}
