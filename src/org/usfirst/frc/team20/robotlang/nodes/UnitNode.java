package org.usfirst.frc.team20.robotlang.nodes;
import org.usfirst.frc.team20.robotlang.Node;
import java.util.List;

import org.usfirst.frc.team20.robotlang.SyntaxException;
import org.usfirst.frc.team20.robotlang.Unit;
/**
 *
 * @author Jared Gentner
 */
public class UnitNode extends ValueNode{

    private Unit.Measure unit;
    
    public UnitNode(Unit.Measure unit){
        this.unit = unit;
    }

    @Override public Object getValue() throws SyntaxException{
        List<Node> children = getChildren();
        Node child = children.get(0);
        return new Unit((double)child.getValue(), unit);
    }
}
