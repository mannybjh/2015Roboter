package org.usfirst.frc.team20.robotlang;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jared Gentner
 */
public class TestInterpreter implements Interpreter {

	private long delay;
	private Lexer lexer;
	private ParseTree tree;
	private Map<Object, Object> symbols = new HashMap<>();
	private Map<Object, InterpreterCommand> commands = new HashMap<>();
	private List<InterpreterCommand> executing = new ArrayList<>();

	public TestInterpreter(long delay) {
		this.delay = delay;
	}

	@Override
	public void setLexer(Lexer lexer) {
		this.lexer = lexer;
	}

	@Override
	public void setParseTree(ParseTree tree) {
		this.tree = tree;
	}

	@Override
	public void interpret(String line) throws SyntaxException {
		Lexer lexer = new Lexer(new RobotStateTable().createStateTable());
		lexer.setStream(new ByteArrayInputStream(line.getBytes()));
		Token token = null;
		List<Token> tokens = new ArrayList<>();
		do {
			try {
				tokens.add(token = lexer.nextToken());
			} catch (IOException ex) {
				System.err.println("nextToken failed");
				ex.printStackTrace();
			}
		} while (token.id != State.Id.EOF && token.id != State.Id.ERROR);
		if (token.id == State.Id.ERROR) {
			throw new SyntaxException("Parse Failed");
		}

		Node node = tree.parse(tokens);
		node.getValue();
		while (!executing.isEmpty()) {
			for (int i = 0; i < executing.size(); ++i) {
				InterpreterCommand command = executing.get(i);
				if (!command.isFinished())
					command.execute();
				else
					executing.remove(i--);
			}
			try {
				Thread.sleep(delay);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void interpret(InputStream line) {

	}

	@Override
	public Object getSymbol(Object key) {
		Object value = symbols.get(key);
		return value;
	}

	@Override
	public void setSymbol(Object key, Object value) {
		symbols.put(key, value);
	}

	@Override
	public InterpreterCommand getCommand(Object key) {
		InterpreterCommand command = commands.get(key).getNewCommand();
		return command;
	}

	@Override
	public void setCommand(Object key, InterpreterCommand value) {
		commands.put(key, value);
	}

	@Override
	public void executeCommand(InterpreterCommand command) {
		executing.add(command);
	}
}
