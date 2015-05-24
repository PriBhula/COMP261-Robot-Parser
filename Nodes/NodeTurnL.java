package Nodes;

import java.util.Scanner;

import Core.*;
import Interfaces.RobotProgramNode;

public class NodeTurnL implements RobotProgramNode {
	
	@Override
	public void execute(Robot robot) {
		robot.turnLeft();
	}
	
	@Override
	public RobotProgramNode parse(Scanner s) {
		if (!Parser.gobble(Parser.TURNL, s)) {
			Parser.fail("turnL fail. expecting: " + Parser.TURNL.toString(), s);
		}
		return this;
	}
	
	public String toString(){
		return ("turnL");
	}

}
