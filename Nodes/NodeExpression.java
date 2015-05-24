package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;

public class NodeExpression implements RobotExpressionNode {
	private RobotExpressionNode node = null;
	
	@Override
	public int evaluate(Robot robot) {
		return node.evaluate(robot);
	}

	@Override
	public RobotExpressionNode parse(Scanner s) {
		if (s.hasNext(Parser.SENSOR)) {
			node = new NodeSensor();
		}
		else if (s.hasNext(Parser.NUM)) {
			node = new NodeNumber();
		}
		else if (s.hasNext(Parser.OPERATION)) {
			node = new NodeOperation();
		}
		else{
			Parser.fail("nodeexp fail.", s);
		}
		node.parse(s);
		return node;
	}
	
	public String toString() {
		return node.toString();
	}

}
