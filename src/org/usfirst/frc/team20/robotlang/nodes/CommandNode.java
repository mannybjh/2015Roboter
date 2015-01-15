package org.usfirst.frc.team20.robotlang.nodes;
import java.util.List;

import org.usfirst.frc.team20.robotlang.Interpreter;
import org.usfirst.frc.team20.robotlang.Node;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class CommandNode extends Node{

    private Interpreter interpreter;

    public CommandNode(Interpreter interpreter){
        this.interpreter = interpreter;
    }
    
    @Override public Object getValue() throws SyntaxException{
        List<Node> children = super.getChildren();
        if(children.size() != 1)
            return null;
        Node command = children.get(0);
        if(!(command instanceof NameNode))
            throw new SyntaxException("Command identifier should be a string");
        String key = (String)command.getValue();
        try{
            return interpreter.getCommand(key);
        }catch(NullPointerException ex){
            throw new SyntaxException("Command " + key + " not found");
        }
    }
}
