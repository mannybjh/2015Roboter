package org.usfirst.frc.team20.subsystems;

import org.usfirst.frc.team20.robot.RobotEvent;

public interface IDrivetrain extends ISubsystem{
	
	AccelerationBehavior getAccelerationBehavior();
	
	DriveBehavior getDriveBehavior();
	
	SpeedControllerMap getSpeedControllerMap();
	
	void setAccelerationBehavior(AccelerationBehavior behavior);
	
	void setDriveBehavior(DriveBehavior behavior);
	
	void setSpeedControllerMap(SpeedControllerMap map);
	
	@Override public void handleRobotEvent(RobotEvent evt);
}
