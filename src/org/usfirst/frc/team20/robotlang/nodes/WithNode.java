package org.usfirst.frc.team20.robotlang.nodes;
import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team20.robotlang.Node;
import org.usfirst.frc.team20.robotlang.SyntaxException;
/**
 *
 * @author Jared Gentner
 */
public class WithNode extends Node{
    
    @Override public Object getValue() throws SyntaxException{
        Map<String, Object> value = new HashMap<>();
        for(Node child : getChildren()){
            if(!(child instanceof AsNode))
                throw new SyntaxException("With must be followed by as statements");
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>)child.getValue();
            value.put((String)entry.getKey(), entry.getValue());
        }
        return value;
    }
}
