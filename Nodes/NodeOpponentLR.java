package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;

public class NodeOpponentLR implements RobotExpressionNode {

	@Override
	public int evaluate(Robot robot) {
		return robot.getOpponentLR();
	}

	@Override
	public RobotExpressionNode parse(Scanner s) {
		if (!Parser.gobble(Parser.OPPLR, s)) {
			Parser.fail("opplr fail. expecting: " + Parser.OPPLR.toString(), s);
		}
	
		return this;
	}
	
	public String toString() {
		return Parser.OPPLR.toString();
	}
	
	

}
