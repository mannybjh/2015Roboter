package org.usfirst.frc.team20.robotlang.nodes;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class LessThanNode extends LogicOpNode{
    
    @Override public boolean evaluate(Object op1, Object op2) throws SyntaxException{
        if(!(op1 instanceof Double && op2 instanceof Double))
            throw new SyntaxException("Both operands should be integers for less than");
        return (Double)op1 < (Double)op2;
    }
}
