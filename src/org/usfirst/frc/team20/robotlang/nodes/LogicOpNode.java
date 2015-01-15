package org.usfirst.frc.team20.robotlang.nodes;
import java.util.List;

import org.usfirst.frc.team20.robotlang.Node;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public abstract class LogicOpNode extends Node{
    
    @Override public Object getValue() throws SyntaxException{
        List<Node> children = getChildren();        
        if(children.size() != 2)
            throw new SyntaxException("Binay Logic Operators require two operands");
        return evaluate(children.get(0).getValue(), children.get(1).getValue());
    }

    public abstract boolean evaluate(Object op1, Object op2) throws SyntaxException;
}
