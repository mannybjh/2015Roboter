package org.usfirst.frc.team20.subsystem;

public class DefaultAccelerationBehavior implements AccelerationBehavior{

	@Override public DriveInput accelerate(DriveInput input) {
		return input;
	}

}
