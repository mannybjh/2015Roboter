package org.usfirst.frc.team20.robotlang;

/**
 *
 * @author Jared Gentner
 */
public class RobotStateTable implements StateTable {

	@Override
	public State createStateTable() {
		State root = new State(State.Id.INDETERMINATE);
		root.setNextState('\004', State.EOF);

		Terminals terms = new Terminals();
		terms.addTerminal("-$@=<>();,\"\004" + State.whitespace + State.letters
				+ State.numbers);

		terms.setEnabled(State.whitespace, false);
		State whitespace = new State(State.Id.INDETERMINATE);
		whitespace.setNextState(State.whitespace, whitespace);
		whitespace.setNextState(terms.toString(),
				new State(State.Id.WHITESPACE));
		terms.setEnabled(State.whitespace, true);
		root.setNextState(State.whitespace, whitespace);

		State temp;

		terms.setEnabled(State.numbers + State.letters, false);
		State name = new State(State.Id.INDETERMINATE);
		name.setNextState(State.letters + State.numbers, name);
		name.setNextState(terms.toString(), new State(State.Id.NAME));
		root.setNextState(State.letters, name);

		terms.setEnabled(State.letters, true);
		State integer = new State(State.Id.INDETERMINATE);
		integer.setNextState(State.numbers, integer);
		integer.setNextState(terms.toString(), new State(State.Id.NUMBER));
		root.setNextState(State.numbers, integer);

		State decimal = new State(State.Id.INDETERMINATE);
		decimal.setNextState(State.numbers, decimal);
		decimal.setNextState(terms.toString(), new State(State.Id.NUMBER));
		integer.setNextState('.', decimal);
		root.setNextState('.', decimal);

		terms.setEnabled(State.numbers + State.letters, false);
		State defaults = new State(State.Id.INDETERMINATE);
		defaults.setNextState(State.letters + State.numbers, name);
		defaults.setNextState(terms.toString(), new State(State.Id.NAME));

		temp = root.addString("\"");
		State string = new State(State.Id.INDETERMINATE);
		temp.setAllStates(string);
		temp.setNextState("\004", new State(State.Id.ERROR));
		string.setAllStates(string);
		string.setNextState("\004", new State(State.Id.ERROR));
		State stringdone = new State(State.Id.INDETERMINATE);
		string.setNextState('"', stringdone);
		stringdone.setAllStates(new State(State.Id.STRING));
		stringdone.setNextState("\004", new State(State.Id.ERROR));

		temp = root.addString("\"\"", string);
		temp.setNextState(terms.toString(), new State(State.Id.STRING));

		temp = root.addString("deg", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.DEGREES));

		temp = root.addString("ft", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.FEET));

		temp = root.addString("m", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.METERS));

		temp = root.addString("rad", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.RADIANS));

		temp = root.addString("do", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.DO));

		temp = root.addString("while", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.WHILE));

		temp = root.addString("until", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.UNTIL));

		temp = root.addString("as", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.AS));

		temp = root.addString("and", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.AND));

		temp = root.addString("or", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.OR));

		temp = root.addString("not", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.NOT));

		temp = root.addString("if", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.IF));

		temp = root.addString("then", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.THEN));

		temp = root.addString("else", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.ELSE));

		temp = root.addString("with", defaults);
		temp.setNextState(terms.toString(), new State(State.Id.WITH));

		terms.setEnabled(State.numbers + State.letters, true);

		terms.setEnabled("=<>", false);

		temp = root.addString("@");
		temp.setNextState(terms.toString(), new State(State.Id.COMMAND));

		temp = root.addString(";");
		temp.setNextState(terms.toString(), new State(State.Id.END));

		temp = root.addString("$");
		temp.setNextState(terms.toString(), new State(State.Id.VAR));

		temp = root.addString("-");
		temp.setNextState(State.numbers, integer);

		temp = root.addString(",");
		temp.setNextState(terms.toString(), new State(State.Id.DELIMITER));

		temp = root.addString("(");
		temp.setNextState(terms.toString(), new State(State.Id.OPENPAREN));

		temp = root.addString(")");
		temp.setNextState(terms.toString(), new State(State.Id.CLOSEPAREN));

		temp = root.addString("=");
		temp.setNextState(terms.toString(), new State(State.Id.ASSIGN));

		temp = root.addString("==", temp);
		temp.setNextState(terms.toString(), new State(State.Id.EQUALTO));

		temp = root.addString("<");
		temp.setNextState(terms.toString(), new State(State.Id.LESSTHAN));

		temp = root.addString("<=", temp);
		temp.setNextState(terms.toString(), new State(State.Id.LESSTHANEQUALTO));

		temp = root.addString(">");
		temp.setNextState(terms.toString(), new State(State.Id.GREATERTHAN));

		temp = root.addString(">=", temp);
		temp.setNextState(terms.toString(), new State(
				State.Id.GREATERTHANEQUALTO));

		terms.setEnabled("=<>", true);
		return root;
	}
}
