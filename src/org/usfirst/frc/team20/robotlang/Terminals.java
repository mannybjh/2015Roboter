package org.usfirst.frc.team20.robotlang;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Jared Gentner
 */
public class Terminals {

	private Map<Character, Boolean> terminals;

	public Terminals() {
		terminals = new TreeMap<>();
	}

	public void addTerminal(char ch) {
		terminals.put(ch, true);
	}

	public void addTerminal(String string) {
		for (char ch : string.toCharArray())
			addTerminal(ch);
	}

	public void setEnabled(char ch, boolean enabled) {
		terminals.remove(ch);
		terminals.put(ch, enabled);
	}

	public void setEnabled(String string, boolean enabled) {
		for (char ch : string.toCharArray()) {
			setEnabled(ch, enabled);
		}
	}

	public boolean getStateEnabled(char ch) {
		return terminals.get(ch);
	}

	@Override
	public String toString() {
		String tokens = "";
		for (Map.Entry<Character, Boolean> entry : terminals.entrySet()) {
			if (entry.getValue())
				tokens += entry.getKey();
		}
		return tokens;
	}
}
