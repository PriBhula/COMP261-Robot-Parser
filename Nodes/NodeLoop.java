package Nodes;
import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.*;


public class NodeLoop implements RobotProgramNode {

	RobotProgramNode node = null;
	
	@Override
	public void execute(Robot robot) {
		while(true){
			node.execute(robot);
		}
	}

	@Override
	public RobotProgramNode parse(Scanner s) {
		if(!Parser.gobble(Parser.LOOP, s)){
			Parser.fail("loop fail. expecting: "+Parser.LOOP.toString(), s);
		}
		
		node = new NodeBlock();
		node.parse(s);
		return node;
		
	}
	
	public String toString() {
		String s = node.toString();
		return String.format("loop %s",s);
	}

}
