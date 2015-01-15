package org.usfirst.frc.team20.robotlang;

/**
 *
 * @author Jared Gentner
 */
public class Unit {

	public enum Measure {
		FEET, METERS, DEGREES, RADIANS,
	}

	public Measure unit;
	public double value;

	public Unit(double value, Measure unit) {
		this.value = value;
		this.unit = unit;
	}

	@Override
	public String toString() {
		return String.valueOf(value) + " " + this.unit.toString();
	}
}
