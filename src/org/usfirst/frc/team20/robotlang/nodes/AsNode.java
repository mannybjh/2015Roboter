package org.usfirst.frc.team20.robotlang.nodes;
import java.util.List;
import java.util.Map;

import org.usfirst.frc.team20.robotlang.Node;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class AsNode extends Node{
    
    @Override public Object getValue() throws SyntaxException{
        List<Node> children = super.getChildren();
        if(children.size() != 2)
            throw new SyntaxException("As is a binary operator");
        final Node valuenode = children.get(0);
        if(!(valuenode instanceof ValueNode))
            throw new SyntaxException("As binds a value to a name");
        final Node namenode = children.get(1);
        if(!(namenode instanceof NameNode))
            throw new SyntaxException("As binds a value to a name");
        final Object name = namenode.getValue();
        final Object value = valuenode.getValue();
        return new Map.Entry<String, Object>(){
            @Override public String getKey() {
                return (String)name;
            }

            @Override public Object getValue() {
                return value;
            }

            @Override public Object setValue(Object object){return null;}
        };
    }
}
