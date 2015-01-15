package org.usfirst.frc.team20.robotlang.nodes;
import org.usfirst.frc.team20.robotlang.Node;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class NameNode extends Node{

    private String value;

    public NameNode(String value){
        this.value = value;
    }
    
    @Override public Object getValue() throws SyntaxException{
        return value;
    }
}
