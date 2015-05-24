package Nodes;
import java.util.Scanner;
import Core.Parser;import Core.Robot;import Interfaces.*;	

public class NodeTakeFuel implements RobotProgramNode {
	RobotProgramNode rpn;
	@Override
	public void execute(Robot robot) {
		robot.takeFuel();
	}
	
	@Override
	public RobotProgramNode parse(Scanner s) {
		if(!Parser.gobble(Parser.TAKEFUEL, s)){			Parser.fail("takefuel fail. expecting: "+Parser.TAKEFUEL.toString(), s);		}		return this;	}
	
	public String toString(){
		return ("takeFuel");
	}


}
