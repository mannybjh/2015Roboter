package org.usfirst.frc.team20.robotlang;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import org.usfirst.frc.team20.robotlang.nodes.AndNode;
import org.usfirst.frc.team20.robotlang.nodes.AsNode;
import org.usfirst.frc.team20.robotlang.nodes.AssignNode;
import org.usfirst.frc.team20.robotlang.nodes.CommandNode;
import org.usfirst.frc.team20.robotlang.nodes.DoNode;
import org.usfirst.frc.team20.robotlang.nodes.GreaterThanNode;
import org.usfirst.frc.team20.robotlang.nodes.LessThanEqualToNode;
import org.usfirst.frc.team20.robotlang.nodes.LessThanNode;
import org.usfirst.frc.team20.robotlang.nodes.NameNode;
import org.usfirst.frc.team20.robotlang.nodes.NumberNode;
import org.usfirst.frc.team20.robotlang.nodes.OrNode;
import org.usfirst.frc.team20.robotlang.nodes.RootNode;
import org.usfirst.frc.team20.robotlang.nodes.StringNode;
import org.usfirst.frc.team20.robotlang.nodes.UnitNode;
import org.usfirst.frc.team20.robotlang.nodes.VariableNode;
import org.usfirst.frc.team20.robotlang.nodes.WhileNode;
import org.usfirst.frc.team20.robotlang.nodes.WithNode;

/**
 *
 * @author Jared Gentner
 */
public class ParseTree {

	Interpreter interpreter;
	Token lookahead = null;
	Node node = new RootNode();
	Deque<Node> stack = new ArrayDeque<>();
	List<Token> tokens;

	public ParseTree(Interpreter interpreter) {
		this.interpreter = interpreter;
	}

	public Node parse(List<Token> tokens) throws SyntaxException {
		for (int i = 0; i < tokens.size(); ++i) {
			if (tokens.get(i).id == State.Id.WHITESPACE)
				tokens.remove(i);
		}
		this.tokens = tokens;
		System.out.println(tokens);
		if (this.tokens.size() == 0)
			return null;
		nextToken();
		handleStatement();
		if (tokens.size() != 0)
			throw new SyntaxException("Syntax Error");
		System.out.println(node);
		return this.node;
	}

	private void handleStatement() {
		handleDo();
		handleName();
		handleDelimiter();
	}

	private void handleAssign() {
		if (lookahead.id != State.Id.ASSIGN)
			return;
		Node newnode = new AssignNode(this.interpreter);
		addNodeAsParent(newnode);
		stack.push(node);
		node = newnode;
		nextToken();
		handleValue();
		handleCondition();
		handleCommand();
		node = newnode;
		handleDelimiter();
		node = stack.pop();
	}

	private void handleName() {
		if (lookahead.id != State.Id.NAME)
			return;
		Node newnode = new NameNode(lookahead.token);
		node.addChild(newnode);
		stack.push(node);
		node = newnode;
		nextToken();
		handleAssign();
		node = stack.pop();
	}

	private void handleDo() {
		if (lookahead.id != State.Id.DO)
			return;
		Node newnode = new DoNode(this.interpreter);
		node.addChild(newnode);
		stack.push(node);
		node = newnode;
		nextToken();
		handleVariable();
		handleCommand();
		node = stack.pop();
	}

	private void handleCommand() {
		if (lookahead.id != State.Id.COMMAND)
			return;
		Node newnode = new CommandNode(this.interpreter);
		node.addChild(newnode);
		stack.push(node);
		node = newnode;
		nextToken();
		handleName();
		handleWhile();
		node = stack.pop();
		handleWith();
	}

	private void handleVariable() {
		if (lookahead.id != State.Id.VAR)
			return;
		Node newnode = new VariableNode(this.interpreter);
		node.addChild(newnode);
		stack.push(node);
		node = newnode;
		nextToken();
		handleName();
		node = stack.pop();
	}

	private void handleUntil() {
		if (lookahead.id != State.Id.UNTIL)
			return;
		nextToken();
		handleCondition();
	}

	private void handleWhile() {
		if (lookahead.id != State.Id.WHILE)
			return;
		Node newnode = new WhileNode();
		addNodeAsParent(newnode);
		stack.push(node);
		node = newnode;
		nextToken();
		handleCondition();
		node = stack.pop();
	}

	private void handleWith() {
		if (lookahead.id != State.Id.WITH)
			return;
		Node newnode = new WithNode();
		node.addChild(newnode);
		stack.push(node);
		node = newnode;
		nextToken();
		handleValue();
		node = stack.pop();
	}

	private void handleValue() {
		stack.push(node);
		handleString();
		handleNumber();
		handleVariable();
		handleAs();
		node = stack.pop();
	}

	private void handleString() {
		if (lookahead.id != State.Id.STRING)
			return;
		Node newnode = new StringNode(lookahead.token);
		node.addChild(newnode);
		node = newnode;
		nextToken();
	}

	private void handleNumber() {
		if (lookahead.id != State.Id.NUMBER)
			return;
		Node newnode = new NumberNode(Double.parseDouble(lookahead.token));
		node.addChild(newnode);
		node = newnode;
		nextToken();
		handleUnit();
		handleLogicOp();
	}

	private void handleUnit() {
		handleFeet();
		handleMeters();
		handleDegrees();
		handleRadians();
	}

	private void handleFeet() {
		if (lookahead.id != State.Id.FEET)
			return;
		Node newnode = new UnitNode(Unit.Measure.FEET);
		addNodeAsParent(newnode);
		node = newnode;
		nextToken();
	}

	private void handleMeters() {
		if (lookahead.id != State.Id.METERS)
			return;
		Node newnode = new UnitNode(Unit.Measure.METERS);
		addNodeAsParent(newnode);
		node = newnode;
		nextToken();
	}

	private void handleDegrees() {
		if (lookahead.id != State.Id.DEGREES)
			return;
		Node newnode = new UnitNode(Unit.Measure.DEGREES);
		addNodeAsParent(newnode);
		node = newnode;
		nextToken();
	}

	private void handleRadians() {
		if (lookahead.id != State.Id.RADIANS)
			return;
		Node newnode = new UnitNode(Unit.Measure.RADIANS);
		addNodeAsParent(newnode);
		node = newnode;
		nextToken();
	}

	private void handleAs() {
		if (lookahead.id != State.Id.AS)
			return;
		Node newnode = new AsNode();
		addNodeAsParent(newnode);
		stack.push(node);
		node = newnode;
		nextToken();
		handleName();
		handleDelimiter();
		handleLineEnd();
		node = stack.pop();
	}

	public void handleDelimiter() {
		if (lookahead.id != State.Id.DELIMITER)
			return;
		node = node.getParent();
		stack.push(node);
		nextToken();
		handleName();
		handleValue();
		node = stack.pop();
	}

	public void handleLineEnd() {
		if (lookahead.id != State.Id.END)
			return;
		System.out.println("Line End");
		while (node.getParent() != null)
			node = node.getParent();
		nextToken();
		handleStatement();
	}

	private void handleCondition() {
		handleValue();
		handleLogicOp();
	}

	private void handleLogicOp() {
		handleAnd();
		handleOr();
		handleGreaterThanEq();
		handleLessThanEq();
		handleGreaterThan();
		handleLessThan();
	}

	private void handleOr() {
		if (lookahead.id != State.Id.OR)
			return;
		Node newnode = new OrNode();
		addNodeAsParent(newnode);
		nextToken();
	}

	private void handleAnd() {
		if (lookahead.id != State.Id.AND)
			return;
		Node newnode = new AndNode();
		addNodeAsParent(newnode);
		nextToken();
	}

	private void handleGreaterThanEq() {
		if (lookahead.id != State.Id.GREATERTHANEQUALTO)
			return;
		Node newnode = new GreaterThanNode();
		addNodeAsParent(newnode);
		stack.push(node);
		node = newnode;
		nextToken();
		handleNumber();
	}

	private void handleLessThanEq() {
		if (lookahead.id != State.Id.LESSTHANEQUALTO)
			return;
		Node newnode = new LessThanEqualToNode();
		addNodeAsParent(newnode);
		stack.push(node);
		node = newnode;
		nextToken();
		handleNumber();
		node = stack.pop();
	}

	private void handleLessThan() {
		if (lookahead.id != State.Id.LESSTHAN)
			return;
		Node newnode = new LessThanNode();
		addNodeAsParent(newnode);
		stack.push(node);
		node = newnode;
		nextToken();
		handleNumber();
		node = stack.pop();
	}

	private void handleGreaterThan() {
		if (lookahead.id != State.Id.GREATERTHAN)
			return;
		Node newnode = new GreaterThanNode();
		stack.push(node);
		node = newnode;
		nextToken();
		handleNumber();
		node = stack.pop();
	}

	private void addNodeAsParent(Node newnode) {
		node.getParent().addChild(newnode);
		node.getParent().removeChild(node);
		newnode.addChild(node);
	}

	private void nextToken() {
		if (tokens.size() != 0)
			lookahead = tokens.remove(0);
		else
			lookahead = new Token("", null);
	}

	public static void main(String[] argv) {
		if (argv.length != 1)
			return;
		Lexer lexer = new Lexer(new RobotStateTable().createStateTable());
		Interpreter interpreter = new TestInterpreter(5);
		interpreter.setLexer(lexer);
		ParseTree tree;
		interpreter.setParseTree(tree = new ParseTree(interpreter));
		interpreter.setCommand("print", new PrintCommand());
		try {
			interpreter.interpret(argv[0]);
		} catch (SyntaxException ex) {
			System.out.println("Syntax Error: " + ex.getMessage());
		}
	}
}
