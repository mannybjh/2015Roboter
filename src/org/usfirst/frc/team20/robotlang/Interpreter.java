package org.usfirst.frc.team20.robotlang;

import java.io.InputStream;

/**
 *
 * @author Jared Gentner
 */
public interface Interpreter {

	void interpret(String line) throws SyntaxException;

	void interpret(InputStream stream) throws SyntaxException;

	Object getSymbol(Object key);

	void setSymbol(Object key, Object value);

	InterpreterCommand getCommand(Object key);

	void setCommand(Object key, InterpreterCommand value);

	void executeCommand(InterpreterCommand command);

	void setLexer(Lexer lexer);

	void setParseTree(ParseTree parseTree);
}
