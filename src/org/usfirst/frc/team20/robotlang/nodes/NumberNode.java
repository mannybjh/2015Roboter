package org.usfirst.frc.team20.robotlang.nodes;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class NumberNode extends ValueNode{
    
    private double value;

    public NumberNode(double value){
        this.value = value;
    }
    
    @Override public Object getValue() throws SyntaxException{
        return value;
    }
}
