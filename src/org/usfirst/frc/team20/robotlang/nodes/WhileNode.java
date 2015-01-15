package org.usfirst.frc.team20.robotlang.nodes;
import java.util.List;

import org.usfirst.frc.team20.robotlang.InterpreterCommand;
import org.usfirst.frc.team20.robotlang.LoopCommand;
import org.usfirst.frc.team20.robotlang.Node;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class WhileNode extends Node{
    
    @Override public Object getValue() throws SyntaxException{
        List<Node> children = getChildren();
        if(children.size() != 2)
            throw new SyntaxException("Syntax is do <command> while <condition>");
        final Node condition = children.get(1);
        final Node command = children.get(0);
        if(!(condition instanceof LogicOpNode))
            throw new SyntaxException("While needs a condition");
        if(!(command instanceof CommandNode))
            throw new SyntaxException("While should be preceeded by a command");
        return new LoopCommand((InterpreterCommand)command.getValue()){

            public boolean validate() throws SyntaxException{
                return (boolean)condition.getValue();
            }
        };
    }
}
