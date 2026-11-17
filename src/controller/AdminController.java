package controller;

import java.util.Map;

import domain.Vehicle.VehicleType;
import service.AdminService;

public class AdminController {

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	public void addFloor(int floorNumber) {
		try {
			adminService.addFloor(floorNumber);
			System.out.println("Floor " + floorNumber + " added successfully.");
		} catch (IllegalArgumentException | IllegalStateException e) {
			System.out.println("Could not add floor. " + e.getMessage());
		}
	}

	public void addSlotsToFloor(int floorNumber, VehicleType vehicleType, int count) {
		try {
			adminService.addSlotsToFloor(floorNumber, vehicleType, count);
			String slotLabel = count == 1 ? "slot" : "slots";
			System.out.printf("Added %d %s %s to floor %d.%n", count, vehicleType, slotLabel, floorNumber);
		} catch (IllegalArgumentException | IllegalStateException e) {
			System.out.println("Could not add parking slots. " + e.getMessage());
		}
	}

	public void addPricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {
		try {
			adminService.addPricingRule(vehicleType, ratePerHour, flatRate);
			System.out.println("Pricing rule added for " + vehicleType + ".");
		} catch (IllegalArgumentException | IllegalStateException e) {
			System.out.println("Could not add pricing rule. " + e.getMessage());
		}
	}

	public void updatePricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {
		try {
			adminService.updatePricingRule(vehicleType, ratePerHour, flatRate);
			System.out.println("Hourly and flat rates updated for " + vehicleType + ".");
		} catch (IllegalArgumentException | IllegalStateException e) {
			System.out.println("Could not update pricing rule. " + e.getMessage());
		}
	}

	public void updateFlatPricing(VehicleType vehicleType, double flatRate) {
		try {
			adminService.updateFlatPricing(vehicleType, flatRate);
			System.out.println("Flat rate updated for " + vehicleType + ".");
		} catch (IllegalArgumentException | IllegalStateException e) {
			System.out.println("Could not update flat rate. " + e.getMessage());
		}
	}

	public void updateHourlyPricing(VehicleType vehicleType, double ratePerHour) {
		try {
			adminService.updateHourlyPricing(vehicleType, ratePerHour);
			System.out.println("Hourly rate updated for " + vehicleType + ".");
		} catch (IllegalArgumentException | IllegalStateException e) {
			System.out.println("Could not update hourly rate. " + e.getMessage());
		}
	}

	public Map<String, Object> getParkingStatus() {
		Map<String, Object> status = adminService.getParkingStatus();
		System.out.println("Parking status retrieved successfully.");
		return status;
	}
}
