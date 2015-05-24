package Nodes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.*;


public class NodeBlock implements RobotProgramNode{

	public List<RobotProgramNode> blocks = new ArrayList<RobotProgramNode>();
	
	public void execute(Robot robot) {
		for(RobotProgramNode r:blocks){
			r.execute(robot);
		}
	}

	@Override
	public RobotProgramNode parse(Scanner s) {
		RobotProgramNode node = null;
		if(!Parser.gobble(Parser.OPENBRACE, s)){
			Parser.fail("openbrace fail. expecting: "+Parser.OPENBRACE.toString(), s);
		}
		while(!s.hasNext(Parser.CLOSEBRACE)){
			if(s.hasNext()){
				node = new NodeStatement();
				node.parse(s);
				blocks.add(node);
			}
		}
		if (!Parser.gobble(Parser.CLOSEBRACE, s)) {
			Parser.fail("closebrace fail. expecting: " + Parser.CLOSEBRACE.toString(), s);
		}
		
		return this;
	}
	
	public String toString() {
		String s = "{";
		for (RobotProgramNode r : blocks) {
			s += "" + r.toString();
		}
		return (s + " }\n");
	}

}
