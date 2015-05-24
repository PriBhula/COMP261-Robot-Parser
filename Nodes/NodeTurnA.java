package Nodes;
import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotProgramNode;


public class NodeTurnA implements RobotProgramNode {

	@Override
	public void execute(Robot robot) {
		robot.turnAround();
	}

	@Override
	public RobotProgramNode parse(Scanner s) {
		if (!Parser.gobble(Parser.TURNAROUND, s)) {
			Parser.fail("turnAround fail. expecting: " + Parser.TURNAROUND.toString(), s);
		}
		return this;
	}
	
	public String toString() {
		return "turnAround";
	}

}
