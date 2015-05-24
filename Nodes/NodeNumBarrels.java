package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;

public class NodeNumBarrels implements RobotExpressionNode {

	@Override
	public int evaluate(Robot robot) {
		return robot.numBarrels();
	}

	@Override
	public RobotExpressionNode parse(Scanner s) {
		if(!Parser.gobble(Parser.NUMBARRELS, s)){
			Parser.fail("numbarrel fail. expecting: "+Parser.NUMBARRELS.toString(), s);
		}
		return this;
	}
	
	public String toString() {
		return Parser.NUMBARRELS.toString();
	}

}
