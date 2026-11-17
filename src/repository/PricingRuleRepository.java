package repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import domain.PricingRule;
import domain.Vehicle.VehicleType;

public class PricingRuleRepository {

	private Map<UUID, PricingRule> rules = new ConcurrentHashMap<>();
	private Map<VehicleType, UUID> vehicleTypeToRule = new ConcurrentHashMap<>();

	public boolean existsByVehicleType(VehicleType type) {
		UUID ruleId = vehicleTypeToRule.get(type);

		if (ruleId == null) {
			return false;
		}

		return rules.containsKey(ruleId);
	}

	public PricingRule save(PricingRule rule) {
		rules.put(rule.getId(), rule);
		vehicleTypeToRule.put(rule.getVehicleType(), rule.getId());

		return rule;
	}

	public PricingRule findByVehicleType(VehicleType type) {
		UUID ruleId = vehicleTypeToRule.get(type);

		if (ruleId == null) {
			return null;
		}

		return rules.get(ruleId);
	}

	public void update(PricingRule rule) {
		if (rules.containsKey(rule.getId())) {
			rules.put(rule.getId(), rule);
			vehicleTypeToRule.put(rule.getVehicleType(), rule.getId());
		}
	}
}
