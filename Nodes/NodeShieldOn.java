package Nodes;
import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotProgramNode;


public class NodeShieldOn implements RobotProgramNode {
	
	@Override
	public void execute(Robot robot) {
		robot.setShield(true);
	}

	@Override
	public RobotProgramNode parse(Scanner s) {
		if(!Parser.gobble(Parser.SHIELDON, s)){
			Parser.fail("shieldOn failed. expecting: "+Parser.SHIELDON.toString(), s);
		}
		return this;
	}
	
	public String toString(){
		return "shieldOn";
	}

}
