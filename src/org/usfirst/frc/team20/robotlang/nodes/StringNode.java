package org.usfirst.frc.team20.robotlang.nodes;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class StringNode extends ValueNode{

    private String value;

    public StringNode(String value){
        this.value = value.substring(1, value.length()-1);
    }

    @Override public Object getValue() throws SyntaxException{
        return value;
    }
}
