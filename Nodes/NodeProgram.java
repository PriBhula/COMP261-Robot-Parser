package Nodes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Core.Robot;
import Interfaces.RobotProgramNode;


public class NodeProgram implements RobotProgramNode {

	private List<RobotProgramNode> statements = new ArrayList<RobotProgramNode>();
	
	@Override
	public void execute(Robot robot) {
		for(RobotProgramNode r: statements){
				r.execute(robot);
		}
	}
	
	@Override
	public RobotProgramNode parse(Scanner s) {
		NodeStatement ns;
		while(s.hasNext()){
			//System.out.println("hasnext"+s.next());
			ns = new NodeStatement();
			//System.out.println("b4add");
			statements.add(ns.parse(s));
			//System.out.println("added");
		}
		return this;
	}
	
	public String toString(){
		String s = "";
		for (RobotProgramNode r: statements){
			s += r.toString()+";\n";
		}
		return s;
	}

}
