package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;

public class NodeMul implements RobotExpressionNode {
	private NodeExpression nodeA = null;
	private NodeExpression nodeB = null;
	private int valA = -1;
	private int valB = -1;
	
	public int evaluate(Robot robot) {
		valA = nodeA.evaluate(robot);
		valB = nodeB.evaluate(robot);
		return (valA * valB);
	}


	@Override
	public RobotExpressionNode parse(Scanner s) {
	
		if (!Parser.gobble(Parser.MUL, s)) {
			Parser.fail("mul fail. expecting: " + Parser.MUL.toString(), s);
		}

		if (!Parser.gobble(Parser.OPENPAREN, s)) {
			Parser.fail("openparen fail. expecting " + Parser.OPENPAREN.toString(), s);
		}
	
		nodeA = new NodeExpression();
		nodeA.parse(s);
	
		if (!Parser.gobble(Parser.SEMICOL, s)) {
			Parser.fail("comma fail. expecting ,", s);
		}
	
		nodeB = new NodeExpression();
		nodeB.parse(s);
	
		if (!Parser.gobble(Parser.CLOSEPAREN, s)) {
			Parser.fail("closeparen fail. expecitng: " + Parser.CLOSEPAREN.toString(), s);
		}
		return this;
	}

	@Override
	
	public String toString() {

		return String.format("mul ( %s, %s )", nodeA.toString(), nodeB.toString());
	}

}
