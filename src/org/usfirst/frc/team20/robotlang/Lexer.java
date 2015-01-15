package org.usfirst.frc.team20.robotlang;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Jared Gentner
 */
public class Lexer {

	private final State start;
	private InputStream stream;

	public Lexer(State initialState, InputStream stream) {
		this.stream = stream;
		this.start = initialState;
	}

	public Lexer(State initialState) {
		this(initialState, null);
	}

	public InputStream getStream() {
		return this.stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public Token nextToken() throws IOException {
		String token = "";
		State current = start;
		for (;;) {
			stream.mark(1);
			int next = stream.read();
			if (next == -1)
				next = 4;
			char ch = (char) next;
			current = current.nextState(ch);
			State.Id id = current.getId();
			if (id == State.Id.INDETERMINATE)
				token += ch;
			else {
				stream.reset();
				return new Token(token, id);
			}
		}
	}

	public static void main(String[] argv) {
		if (argv.length != 1)
			return;
		State state = new RobotStateTable().createStateTable();
		Token token = null;
		Lexer lexer = new Lexer(state, new ByteArrayInputStream(
				argv[0].getBytes()));
		do {
			try {
				System.out.println(token = lexer.nextToken());
			} catch (IOException ex) {
				System.err.println("nextToken failed");
				ex.printStackTrace();
			}
		} while (token.id != State.Id.EOF && token.id != State.Id.ERROR);
		if (token.id == State.Id.ERROR)
			System.err.println("an error occurred");
	}
}
