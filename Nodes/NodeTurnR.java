package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotProgramNode;


public class NodeTurnR implements RobotProgramNode {
	RobotProgramNode rpn;
	
	@Override
	public void execute(Robot robot) {
		robot.turnRight();
	}
	
	@Override
	public RobotProgramNode parse(Scanner s) {
		if (!Parser.gobble(Parser.TURNR, s)) {
			Parser.fail("turnR fail. expecting: " + Parser.TURNR.toString(), s);
		}
		return this;
	}
	
	public String toString(){
		return ("turnR");
	}

}
