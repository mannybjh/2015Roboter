package org.usfirst.frc.team20.robotlang;

/**
 *
 * @author Jared Gentner
 */
public interface StateTable {

	/**
	 * Implementations should lazy initialize the state table
	 */
	State createStateTable();
}
