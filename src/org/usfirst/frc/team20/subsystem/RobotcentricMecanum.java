package org.usfirst.frc.team20.subsystem;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team20.device.SpeedControllerDevice;
import org.usfirst.frc.team20.robot.Robot;

public class RobotcentricMecanum implements DriveBehavior {
	
	@Override public void drive(DriveInput inputs) {
		double speed = inputs.leftY;
		double hor = inputs.leftX;
		double turn = inputs.shoulder;
		Map<String, Object> properties = new HashMap<>();
		
		String voltageKey = SpeedControllerDevice.VOLTAGE_SUFFIX;
		
		properties.put(Robot.FRONT_LEFT_SC_KEY + voltageKey,
			limit(-(speed + turn - hor)));
		properties.put(Robot.BACK_LEFT_SC_KEY + voltageKey,
			limit(-(speed + turn + hor)));
		properties.put(Robot.FRONT_RIGHT_SC_KEY + voltageKey, 
			limit(speed + turn + hor));
		properties.put(Robot.BACK_RIGHT_SC_KEY + voltageKey, 
			limit(speed + turn - hor));
		
		RobotProperties.setProperties(properties);
	}

	private double limit(double voltage) {
		if (voltage >= 1)
			return 1;
		if (voltage <= -1)
			return -1;
		return voltage;
	}
}
