package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;

public class NodeOperation implements RobotExpressionNode {

	RobotExpressionNode node = null;


	@Override
	public int evaluate(Robot robot) {
		return node.evaluate(robot);
	}

	@Override
	public RobotExpressionNode parse(Scanner s) {

		if (s.hasNext(Parser.ADD)){
			node = new NodeAdd();
		} else if (s.hasNext(Parser.SUB)){
			node = new NodeSub();
		} else if (s.hasNext(Parser.MUL)){
			node = new NodeMul();
		} else if (s.hasNext(Parser.DIV)){
			node = new NodeDiv();
		} 
		node.parse(s);
		return node;
	}

	@Override
	public String toString() {
		return node.toString();
	}

}
