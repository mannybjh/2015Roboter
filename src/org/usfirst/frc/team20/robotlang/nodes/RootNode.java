package org.usfirst.frc.team20.robotlang.nodes;
import org.usfirst.frc.team20.robotlang.Node;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class RootNode extends Node{

    public RootNode(){}
    
    @Override public Object getValue() throws SyntaxException{
        for(Node node : super.getChildren()){
            node.getValue();
        }
        return null;
    }
}
