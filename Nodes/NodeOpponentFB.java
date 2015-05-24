package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;

public class NodeOpponentFB implements RobotExpressionNode {

	@Override
	public int evaluate(Robot robot) {
		return robot.getOpponentFB();
	}

	@Override
	public RobotExpressionNode parse(Scanner s) {
		if(!Parser.gobble(Parser.OPPFB, s)){
			Parser.fail("oppfb fail. expecting: "+Parser.OPPFB.toString(), s);
		}
		return this;
	}
	
	public String toString(){
		return Parser.OPPFB.toString();
	}

}
