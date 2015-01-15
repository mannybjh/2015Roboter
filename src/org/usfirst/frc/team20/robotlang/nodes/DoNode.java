package org.usfirst.frc.team20.robotlang.nodes;
import java.util.List;
import java.util.Map;

import org.usfirst.frc.team20.robotlang.Interpreter;
import org.usfirst.frc.team20.robotlang.InterpreterCommand;
import org.usfirst.frc.team20.robotlang.Node;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class DoNode extends Node{

    private Interpreter interpreter;

    public DoNode(Interpreter interpreter){
        this.interpreter = interpreter;
    }
    
    @Override public Object getValue() throws SyntaxException{
        List<Node> children = super.getChildren();
        boolean hasArgs = true;
        if(children.size() == 2)
            hasArgs = true;
        else if(children.size() == 1)
            hasArgs = false;
        else
            throw new SyntaxException("do syntax is do <command> [while/until] [with]");
        Node commandnode = children.get(0);
        Node args = null;
        if(hasArgs)
            args = children.get(1);
        InterpreterCommand command = ((InterpreterCommand)commandnode.getValue());
        if(!command.isParameterized() && !(args == null))
            command.parameterize((Map<String, Object>)args.getValue());
        interpreter.executeCommand(command);
        return null;
    }
}
