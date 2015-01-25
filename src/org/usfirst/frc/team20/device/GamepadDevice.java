package org.usfirst.frc.team20.device;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team20.robot.T20GamePad;
import org.usfirst.frc.team20.subsystem.DriveInput;

public class GamepadDevice extends Device{
	
	T20GamePad gamepad;

	public GamepadDevice(String namespace, T20GamePad gamepad){
		super(namespace);
		this.gamepad = gamepad;
	}

	@Override public Map<String, Object> getProperties() {
		DriveInput inputs = new DriveInput();
		inputs.shoulder = gamepad.getAxisTrigger();
		inputs.leftX = gamepad.getAxisLeftStickX();
		inputs.leftY = gamepad.getAxisLeftStickY();
		inputs.rightX = gamepad.getAxisRightStickX();
		inputs.rightY = gamepad.getAxisRightStickY();
		Map<String, Object> properties = new HashMap<String, Object>(){{
			put(getNamespace() + "AButton", gamepad.getButtonA());
			put(getNamespace() + "BButton", gamepad.getButtonB());
			put(getNamespace() + "XButton", gamepad.getButtonX());
			put(getNamespace() + "YButton", gamepad.getButtonY());
			put(getNamespace() + "LeftBumper", gamepad.getButtonLB());
			put(getNamespace() + "RightBumper", gamepad.getButtonRB());
			put(getNamespace() + "LeftStick", gamepad.getButtonLS());
			put(getNamespace() + "RightStick", gamepad.getButtonRS());
			put(getNamespace() + "Start", gamepad.getButtonStart());
			put(getNamespace() + "Back", gamepad.getButtonBack());
			put(getNamespace() + "DPadRightX", gamepad.getButtonDPadXR());
			put(getNamespace() + "DPadLeftX", gamepad.getButtonDPadXL());
			put(getNamespace() + "DPadRightY", gamepad.getButtonDPadYR());
			put(getNamespace() + "DPadLeftY", gamepad.getButtonDPadYL());
			put(getNamespace() + "DriveInputs", inputs);
		}};
		return properties;
	}

	@Override public void propertyChanged(String key, Object property) {
		
	}	
}
