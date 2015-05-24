package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;
import Interfaces.*;


public class NodeMove implements RobotProgramNode {
	private RobotExpressionNode rpn;

	@Override
	public void execute(Robot robot) {
		if (rpn != null) {
			int x = rpn.evaluate(robot);
			for (int i = 0; i < x; i++) {
				robot.move();
			}
		} 
		else {
			robot.move();
		}
	}

	@Override
	public RobotProgramNode parse(Scanner s) {
		if (!Parser.gobble(Parser.MOVE, s)) {
			Parser.fail("move fail. expecting: " + Parser.MOVE.toString(), s);
		}

		if (s.hasNext(Parser.OPENPAREN)) {
			Parser.gobble(Parser.OPENPAREN, s);
			rpn = new NodeExpression();
			rpn.parse(s);

			if (s.hasNext(Parser.CLOSEPAREN)) {
				Parser.gobble(Parser.CLOSEPAREN, s);
			} else {
				Parser.fail("closeparen fail. expecting: " + Parser.CLOSEPAREN.toString(), s);
			}
		}
		return this;
	}

	public String toString(){

		String s = "move";
		if (rpn != null) {
			s += String.format(" %s", rpn.toString());
		}
		return s;
	}

}
