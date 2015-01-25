package org.usfirst.frc.team20.robot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc.team20.device.Device;
import org.usfirst.frc.team20.device.DeviceGroup;
import org.usfirst.frc.team20.device.EncoderDevice;
import org.usfirst.frc.team20.device.GamepadDevice;
import org.usfirst.frc.team20.device.SpeedControllerDevice;
import org.usfirst.frc.team20.subsystem.DefaultAccelerationBehavior;
import org.usfirst.frc.team20.subsystem.Drivebase;
import org.usfirst.frc.team20.subsystem.InlinePropertyReader;
import org.usfirst.frc.team20.subsystem.RobotProperties;
import org.usfirst.frc.team20.subsystem.RobotcentricMecanum;
import org.usfirst.frc.team20.subsystem.SimpleParser;

import robotlang.Interpreter;
import robotlang.SyntaxException;
import robotlang.TestInterpreter;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {

	// Namespaces for subsystems to be used with RobotProperties.
	public static final String DRIVEBASE_KEY = "driveBase";

	// Namespaces for devices to be used with RobotProperties.
	public static final String FRONT_RIGHT_SC_KEY = "frontRightSC",
		BACK_RIGHT_SC_KEY = "backRightSC", FRONT_LEFT_SC_KEY = "frontLeftSC",
		BACK_LEFT_SC_KEY = "backLeftSC", RIGHT_ENC_KEY = "rightEncoder",
		LEFT_ENC_KEY = "leftEncoder",
		DRIVER_CONTROLLER_KEY = "driverController",
		OPERATOR_CONTROLLER_KEY = "operatorController";

	// Ports/channels/whatever for devices (must be defined in constants file!
	// [or else])
	public static final int FRONT_RIGHT_SC_PORT, BACK_RIGHT_SC_PORT,
		FRONT_LEFT_SC_PORT, BACK_LEFT_SC_PORT, RIGHT_ENC_CHANNEL_A,
		RIGHT_ENC_CHANNEL_B, LEFT_ENC_CHANNEL_A, LEFT_ENC_CHANNEL_B,
		DRIVER_CONTROLLER_PORT, OPERATOR_CONTROLLER_PORT;

	// Your friendly neighborhood interpreter for auto modes
	private static Interpreter interpreter;
	private static Drivebase drivebase;

	/*
	 * Device groups sort of just for logical grouping of devices
	 */
	private static DeviceGroup speedControllers, encoders, digitalInputs,
		controllers;

	static {
		try {
			Handler handler = new FileHandler("/robot.log");
			Logger.getGlobal().addHandler(handler);
		} catch (SecurityException | IOException ex) {
			System.err.println("Could not attach logger.  Bailing");
			System.exit(-1);
		}

		interpreter = new TestInterpreter(10);

		RobotProperties.setReader(new InlinePropertyReader("|",
			new SimpleParser()));
		try {
			RobotProperties.loadFile("/robot_constants");
		} catch (FileNotFoundException e) {
			Logger.getGlobal().log(Level.SEVERE,
				"Could not load constants. Bailing out.");
			System.exit(-1);
		}

		String[] constantKeys = { FRONT_RIGHT_SC_KEY + "Port",
			FRONT_LEFT_SC_KEY + "Port", BACK_RIGHT_SC_KEY + "Port",
			BACK_LEFT_SC_KEY + "Port", LEFT_ENC_KEY + "Port",
			RIGHT_ENC_KEY + "Port", DRIVER_CONTROLLER_KEY + "Port",
			OPERATOR_CONTROLLER_KEY + "Port" };

		if (!RobotProperties.containsAll(constantKeys)) {
			Logger
				.getGlobal()
				.log(
					Level.SEVERE,
					"Not all constants are defined."
						+ "  Please define all of these constants in constants file: "
						+ constantKeys);
			System.exit(-1);
		}

		DRIVER_CONTROLLER_PORT = (int) RobotProperties
			.getProperty(DRIVER_CONTROLLER_KEY + "Port");
		OPERATOR_CONTROLLER_PORT = (int) RobotProperties
			.getProperty(OPERATOR_CONTROLLER_KEY + "Port");

		FRONT_RIGHT_SC_PORT = (int) RobotProperties
			.getProperty(FRONT_RIGHT_SC_KEY + "Port");
		FRONT_LEFT_SC_PORT = (int) RobotProperties
			.getProperty(FRONT_LEFT_SC_KEY + "Port");
		BACK_RIGHT_SC_PORT = (int) RobotProperties
			.getProperty(BACK_RIGHT_SC_KEY + "Port");
		BACK_LEFT_SC_PORT = (int) RobotProperties.getProperty(BACK_LEFT_SC_KEY
			+ "Port");
		Object[] rightEncChannels = (Object[]) RobotProperties
			.getProperty(RIGHT_ENC_KEY + "Channels");
		Object[] leftEncChannels = (Object[]) RobotProperties
			.getProperty(LEFT_ENC_KEY + "Channels");
		RIGHT_ENC_CHANNEL_A = (int) rightEncChannels[0];
		RIGHT_ENC_CHANNEL_B = (int) rightEncChannels[1];
		LEFT_ENC_CHANNEL_A = (int) leftEncChannels[0];
		LEFT_ENC_CHANNEL_B = (int) leftEncChannels[1];
	}

	@Override protected void robotInit() {

		drivebase = new Drivebase();
		drivebase.setDriveBehavior(new RobotcentricMecanum());
		drivebase.setAccelerationBehavior(new DefaultAccelerationBehavior());

		controllers = new DeviceGroup("controllers");
		speedControllers = new DeviceGroup("speedControllers");
		encoders = new DeviceGroup("encoders");
		digitalInputs = new DeviceGroup("digitalInputs");

		T20GamePad driverGamepad = new T20GamePad(T20GamePad.JS_TYPE_XBOX,
			DRIVER_CONTROLLER_PORT);
		T20GamePad operatorGamepad = new T20GamePad(T20GamePad.JS_TYPE_DRCT,
			OPERATOR_CONTROLLER_PORT);

		SpeedController frontRightSC = new CANTalon(FRONT_RIGHT_SC_PORT), frontLeftSC = new CANTalon(
			FRONT_LEFT_SC_PORT), backRightSC = new CANTalon(BACK_RIGHT_SC_PORT), backLeftSC = new CANTalon(
			BACK_LEFT_SC_PORT);

		Encoder rightEncoder = new Encoder(RIGHT_ENC_CHANNEL_A,
			RIGHT_ENC_CHANNEL_B);
		Encoder leftEncoder = new Encoder(LEFT_ENC_CHANNEL_A,
			LEFT_ENC_CHANNEL_B);

		Device driverControllerDev = new GamepadDevice(DRIVER_CONTROLLER_KEY,
			driverGamepad);
		Device operatorControllerDev = new GamepadDevice(
			OPERATOR_CONTROLLER_KEY, operatorGamepad);

		Device frontRightSCDev = new SpeedControllerDevice(FRONT_RIGHT_SC_KEY,
			frontRightSC);
		Device frontLeftSCDev = new SpeedControllerDevice(FRONT_LEFT_SC_KEY,
			frontLeftSC);
		Device backRightSCDev = new SpeedControllerDevice(BACK_RIGHT_SC_KEY,
			backRightSC);
		Device backLeftSCDev = new SpeedControllerDevice(BACK_LEFT_SC_KEY,
			backLeftSC);

		Device rightEncDev = new EncoderDevice(RIGHT_ENC_KEY, rightEncoder);
		Device leftEncDev = new EncoderDevice(LEFT_ENC_KEY, leftEncoder);

		controllers.addDevice(driverControllerDev);
		controllers.addDevice(operatorControllerDev);

		speedControllers.addDevice(frontRightSCDev);
		speedControllers.addDevice(frontLeftSCDev);
		speedControllers.addDevice(backRightSCDev);
		speedControllers.addDevice(backLeftSCDev);

		encoders.addDevice(rightEncDev);
		encoders.addDevice(leftEncDev);

		RobotProperties.registerPropertyChangeListener(controllers);
		RobotProperties.registerPropertyChangeListener(speedControllers);
		RobotProperties.registerPropertyChangeListener(encoders);

		RobotProperties.setProperties(controllers.getProperties());
		RobotProperties.setProperties(speedControllers.getProperties());
		RobotProperties.setProperties(encoders.getProperties());
	}

	@Override public void autonomous() {
		Runnable runnable = () -> {
			try {
				interpreter.interpret(new FileInputStream(new File(
					"/scriptease")));
			} catch (FileNotFoundException ex) {
				SmartDashboard.putString("autonomous", "Script not found.");
			} catch (SyntaxException ex) {
				SmartDashboard.putString("autonomous", ex.toString());
			}
		};
		Thread auto = new Thread(runnable);
		auto.start();
		while (isAutonomous()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
		}
		interpreter.interrupt();
	}

	@Override public void operatorControl() {
		for (;;) {
			RobotProperties.setProperties(controllers.getProperties());
			RobotProperties.setProperties(speedControllers.getProperties());

		}
	}

	@Override public void test() {

	}
}
