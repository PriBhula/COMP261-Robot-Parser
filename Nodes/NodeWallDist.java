package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;

public class NodeWallDist implements RobotExpressionNode {

	@Override
	public int evaluate(Robot robot) {
		return robot.getDistanceToWall();
	}

	@Override
	public RobotExpressionNode parse(Scanner s) {
		if(!Parser.gobble(Parser.WALLDIST, s)){
			Parser.fail("walldist fail. expecting: "+Parser.WALLDIST.toString(), s);
		}
		return this;
	}
	
	public String toString(){
		return Parser.WALLDIST.toString();
	}

}
