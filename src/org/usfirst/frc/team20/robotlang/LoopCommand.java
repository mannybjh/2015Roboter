package org.usfirst.frc.team20.robotlang;

import java.util.Map;

/**
 *
 * @author Jared Gentner
 */
public abstract class LoopCommand implements InterpreterCommand {

	private InterpreterCommand command;
	private boolean finished;

	public LoopCommand(InterpreterCommand command) {
		this.command = command;
		this.finished = false;
	}

	@Override
	public void execute() {
		try {
			if (validate())
				command.execute();
			else
				finished = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void parameterize(Map<String, Object> context) {
		command.parameterize(context);
	}
	
	@Override public InterpreterCommand getNewCommand(){
		return null;
	}

	@Override
	public boolean isParameterized() {
		return command.isParameterized();
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public void interrupt() {
		command.interrupt();
	}

	public abstract boolean validate() throws SyntaxException;
}
