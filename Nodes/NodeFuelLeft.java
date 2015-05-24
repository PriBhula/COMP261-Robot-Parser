package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;

public class NodeFuelLeft implements RobotExpressionNode {

	@Override
	public int evaluate(Robot robot) {
		return robot.getFuel();
	}

	@Override
	public RobotExpressionNode parse(Scanner s) {
		if(!Parser.gobble(Parser.FUELLEFT, s)){
			Parser.fail("fuelleft fail. expecting: "+Parser.FUELLEFT, s);
		}
		return this;
	}

	public String toString() {
		return Parser.FUELLEFT.toString();
	}
}
