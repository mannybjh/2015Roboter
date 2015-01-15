package org.usfirst.frc.team20.robotlang;

/**
 *
 * @author Jared Gentner
 */
public class State implements Cloneable {

	public enum Id {
		INDETERMINATE, ERROR, EOF, WHITESPACE, COMMAND, VAR, NAME, OPENPAREN, CLOSEPAREN, DO, ASSIGN, EQUALTO, GREATERTHAN, LESSTHAN, GREATERTHANEQUALTO, LESSTHANEQUALTO, AND, OR, NOT, IF, THEN, ELSE, WHILE, UNTIL, DELIMITER, QUOTE, STRING, NUMBER, FEET, METERS, DEGREES, RADIANS, AS, WITH, END
	}

	public static final String letters = "abcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			numbers = "0123456789.-", whitespace = "\b\n\t\r ";

	public static final State ERROR = new State(Id.ERROR), EOF = new State(
			Id.EOF);

	private State[] states;
	private Id id;

	public State(Id id) {
		this.id = id;
		states = new State[256];
		for (int i = 0; i < states.length; ++i) {
			states[i] = ERROR;
		}
	}

	public State(State state) {
		this.id = state.getId();
		this.states = state.getTable();
	}

	public State() {
		this(State.Id.INDETERMINATE);
	}

	public State concat(State state) {
		if (state == null)
			return this;
		State[] stateTable = state.getTable();
		for (int i = 0; i < stateTable.length; ++i) {
			if (stateTable[i] != State.ERROR)
				states[i] = stateTable[i];
		}
		return this;
	}

	public State[] getTable() {
		return java.util.Arrays.copyOf(states, states.length);
	}

	public State nextState(char ch) {
		return states[ch];
	}

	public void setNextState(char key, State state) {
		states[key] = state;
	}

	public void setNextState(String string, State state) {
		for (char ch : string.toCharArray()) {
			setNextState(ch, state);
		}
	}

	public void setAllStates(State state) {
		for (int i = 0; i < 256; ++i) {
			states[(char) i] = state;
		}
	}

	public State addString(String string) {
		return addString(string, new State(State.Id.INDETERMINATE));
	}

	public State addString(String string, State defaults) {
		State state = this;
		for (int i = 0; i < string.length(); ++i) {
			char ch = string.charAt(i);
			state.setNextState(ch,
					new State(defaults).concat(state.nextState(ch)));
			state = state.nextState(ch);
		}
		return state;
	}

	public Id getId() {
		return this.id;
	}

	public void setId(Id id) {
		this.id = id;
	}
}
