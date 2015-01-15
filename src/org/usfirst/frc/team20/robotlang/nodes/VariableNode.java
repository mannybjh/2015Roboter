package org.usfirst.frc.team20.robotlang.nodes;
import org.usfirst.frc.team20.robotlang.Node;
import java.util.List;

import org.usfirst.frc.team20.robotlang.Interpreter;
import org.usfirst.frc.team20.robotlang.Node;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class VariableNode extends ValueNode{

    private Interpreter interpreter;

    public VariableNode(Interpreter interpreter){
        this.interpreter = interpreter;
    }
    
    @Override public Object getValue() throws SyntaxException{
        List<Node> children = super.getChildren();
        if(children.size() != 1)
            return null;
        return interpreter.getSymbol(
            children.get(0).getValue());
    }
}
