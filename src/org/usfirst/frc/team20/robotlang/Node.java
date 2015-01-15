package org.usfirst.frc.team20.robotlang;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author Jared Gentner
 */
public abstract class Node {

	private List<Node> children = new ArrayList<>();
	private Node parent;

	public void addChild(Node child) {
		this.children.add(child);
		child.setParent(this);
	}

	public void removeChild(Node child) {
		this.children.remove(child);
	}

	public List<Node> getChildren() {
		return children;
	}

	public Node getParent() {
		return this.parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public abstract Object getValue() throws SyntaxException;

	@Override
	public String toString() {
		String ret = "|" + this.getClass().getSimpleName() + "\n";
		IndexedNode node = new IndexedNode(this, 0);
		int indent = 0;
		Deque<IndexedNode> nodes = new ArrayDeque<>();
		nodes.push(node);
		while (!nodes.isEmpty()) {
			node = nodes.pop();
			List<Node> children = node.node.getChildren();
			if (!(node.index >= children.size())) {
				++indent;
				Node child = children.get(node.index++);
				nodes.push(node);
				ret += "|";
				for (int i = 0; i < indent * 2; ++i) {
					ret += "_";
				}
				ret += child.getClass().getSimpleName() + "\n";
				nodes.push(new IndexedNode(child, 0));
			} else {
				--indent;
			}
		}
		return ret;
	}

	private class IndexedNode {
		public Node node;
		public int index;

		public IndexedNode(Node node, int index) {
			this.node = node;
			this.index = index;
		}
	}
}
