package org.usfirst.frc.team20.robotlang.nodes;
import java.util.List;

import org.usfirst.frc.team20.robotlang.Interpreter;
import org.usfirst.frc.team20.robotlang.Node;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class AssignNode extends Node{

    private Interpreter interpreter;
    
    public AssignNode(Interpreter interpreter){
        this.interpreter = interpreter;
    }

    @Override public Object getValue() throws SyntaxException{
        List<Node> children = super.getChildren();
        if(children.size() != 2)
            throw new SyntaxException("Assignment is a binary operator");
        Node name = children.get(0);
        if(!(name instanceof NameNode))
            throw new SyntaxException("Assignment binds a name to a value");
        Node value = children.get(1);
        if(!(value instanceof ValueNode))
            throw new SyntaxException("Assignment binds a name to a value");
        interpreter.setSymbol(name.getValue(), value.getValue());
        return value;
    }
}
