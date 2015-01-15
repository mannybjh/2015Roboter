package org.usfirst.frc.team20.robotlang;

import java.util.Map;

/**
 *
 * @author Jared Gentner
 */
public interface InterpreterCommand {

	void execute();

	void parameterize(Map<String, Object> context);

	InterpreterCommand getNewCommand();

	boolean isParameterized();

	boolean isFinished();

	void interrupt();
}
