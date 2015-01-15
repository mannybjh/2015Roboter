package org.usfirst.frc.team20.robotlang;

/**
 *
 * @author Jared Gentner
 */
public class Token {

	public String token;
	public State.Id id;

	public Token(String token, State.Id id) { // begin token constructor
		this.token = token;
		this.id = id;
	}

	@Override
	public String toString() {
		return "<" + id + "," + token + ">";
	}
}
