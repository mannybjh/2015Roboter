package org.usfirst.frc.team20.robotlang.nodes;
import org.usfirst.frc.team20.robotlang.Node;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public abstract class ValueNode extends Node{
    
    @Override public abstract Object getValue() throws SyntaxException;
}
