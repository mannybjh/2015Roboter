package org.usfirst.frc.team20.subsystem;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team20.robot.Robot;

public class Drivebase extends Subsystem {

	public static double WHEEL_DIAMETER;
	private DriveBehavior driveBehavior;
	private AccelerationBehavior accelerationBehavior;

	public Drivebase() {
		initPropertyListeners();
	}

	public void setDriveBehavior(DriveBehavior driveBehavior) {
		this.driveBehavior = driveBehavior;
	}

	public void setAccelerationBehavior(
		AccelerationBehavior accelerationBehavior) {
		this.accelerationBehavior = accelerationBehavior;
	}

	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put("accelerationBehavior", accelerationBehavior.toString());
		properties.put("driveBehavior", driveBehavior.toString());
		return properties;
	}

	private void initPropertyListeners() {
		super.registerPropertyListener(Robot.DRIVER_CONTROLLER_KEY
			+ "DriveInputs", (Object property) -> {
			DriveInput input = (DriveInput) property;
			input = accelerationBehavior.accelerate(input);
			driveBehavior.drive(input);
		});
	}
}
