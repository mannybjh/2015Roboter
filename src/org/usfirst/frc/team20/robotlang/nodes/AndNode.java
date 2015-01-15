package org.usfirst.frc.team20.robotlang.nodes;

import org.usfirst.frc.team20.robotlang.SyntaxException;

/**
 *
 * @author Jared Gentner
 */
public class AndNode extends LogicOpNode{
    
    @Override public boolean evaluate(Object left, Object right) throws SyntaxException{
        if(!(left instanceof Boolean && right instanceof Boolean))
            throw new SyntaxException("And needs two booleans to operate");
        return (boolean)left && (boolean)right;
    }

}
