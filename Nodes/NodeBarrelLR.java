package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;

public class NodeBarrelLR implements RobotExpressionNode {
	public RobotExpressionNode node;
	@Override
	public int evaluate(Robot robot) {
		return robot.getBarrelLR(node.evaluate(robot));

	}

	@Override
	public RobotExpressionNode parse(Scanner s) {
		if (!Parser.gobble(Parser.BARRELLR, s)) {
			Parser.fail("FAIL: Expecting " + Parser.BARRELLR.toString(), s);
		}

		if (s.hasNext(Parser.OPENPAREN)) {
			Parser.gobble(Parser.OPENPAREN, s);
			node = new NodeExpression();
			node.parse(s);
			if (s.hasNext(Parser.CLOSEPAREN)) {
				Parser.gobble(Parser.CLOSEPAREN, s);
			} else {
				Parser.fail("Fail: expected " + Parser.CLOSEPAREN, s);
			}
		}
		return this;
	}

	public String toString() {
		return Parser.BARRELLR.toString();
	}
}
