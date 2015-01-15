package org.usfirst.frc.team20.subsystems;

import org.usfirst.frc.team20.robot.ButtonEventListener;
import org.usfirst.frc.team20.robot.JoystickEventListener;
import org.usfirst.frc.team20.robot.RobotEvent;
import org.usfirst.frc.team20.robot.RobotEventListener;

public interface ISubsystem extends RobotEventListener{

	void addButtonEventListener(ButtonEventListener listener);
	
	void addJoystickEventListener(JoystickEventListener listener);
	
}
