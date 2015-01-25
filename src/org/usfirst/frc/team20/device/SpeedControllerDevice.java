package org.usfirst.frc.team20.device;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.SpeedController;

public class SpeedControllerDevice extends Device {

	public static final String VOLTAGE_SUFFIX = "Voltage";

	private final String VOLTAGE_KEY;
	private final SpeedController speedController;

	public SpeedControllerDevice(String namespace,
		SpeedController speedController) {
		super(namespace);
		this.VOLTAGE_KEY = namespace + VOLTAGE_SUFFIX;
		this.speedController = speedController;
	}

	@Override public Map<String, Object> getProperties() {
		/*
		 * Possible optimization to make a member map and initialize it once,
		 * then repopulate. This is more readable I think but I just wanted to
		 * make note of it.
		 */
		Map<String, Object> properties = new HashMap<>();
		properties.put(getNamespace() + VOLTAGE_KEY, speedController.get());
		return properties;
	}

	@Override public void propertyChanged(String key, Object value) {
		if (key.equals(getNamespace() + VOLTAGE_KEY))// inline for short number of properties
			speedController.set((double) value);
	}
}
