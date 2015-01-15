package org.usfirst.frc.team20.robotlang;

/**
 *
 * @author Jared Gentner
 */
public class SyntaxException extends Exception {

	public SyntaxException(String message) {
		super(message);
	}

	public SyntaxException() {
		this("Syntax Exception");
	}
}
