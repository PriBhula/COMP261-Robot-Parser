package Nodes;
import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotProgramNode;


public class NodeShieldOff implements RobotProgramNode {

	@Override
	public void execute(Robot robot) {
		robot.setShield(false);
	}

	@Override
	public RobotProgramNode parse(Scanner s) {
		if(!Parser.gobble(Parser.SHIELDOFF, s)){
			Parser.fail("shieldOff failed. expecting: "+Parser.SHIELDOFF.toString(), s);
		}
		return this;
	}
	
	public String toString(){
		return "shieldOff";
	}

}
