package org.usfirst.frc.team20.robotlang;

import java.util.Map;

/**
 *
 * @author Jared Gentner
 */
public class PrintCommand implements InterpreterCommand {

	private String text;
	private boolean finished = false;
	private boolean parameterized = false;

	@Override
	public void execute() {
		System.out.println(text);
		finished = true;
	}

	@Override
	public void parameterize(Map<String, Object> context) {
		finished = false;
		text = (String) context.get("text");
		parameterized = true;
	}

	@Override
	public InterpreterCommand getNewCommand() {
		return new PrintCommand();
	}

	@Override
	public boolean isParameterized() {
		return parameterized;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public void interrupt() {
	}
}
