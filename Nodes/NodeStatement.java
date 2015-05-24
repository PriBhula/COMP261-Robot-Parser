package Nodes;

import java.util.Scanner;

import Core.*;
import Interfaces.*;

public class NodeStatement implements RobotProgramNode {

	private RobotProgramNode node;
	
	@Override
	public void execute(Robot robot) {
		node.execute(robot);
	}	

	@Override
	public RobotProgramNode parse(Scanner s) {
		/*if(s.hasNext(Parser.SEMICOL)){
			Parser.gobble(Parser.SEMICOL, s);
		}*/
		if (s.hasNext(Parser.ACTION)) {
			//System.out.println("ACTION");
			node = new NodeAction();
		}
		else if (s.hasNext(Parser.LOOP)) {
			//System.out.println("LOOP");
			node = new NodeLoop();
		} 
		else if (s.hasNext(Parser.IF)) {
			//System.out.println("IF");
			node = new NodeIf();
		} 
		else if (s.hasNext(Parser.WHILE)) {
			//System.out.println("WHILE");
			node = new NodeWhile();
		}
		else{
			Parser.fail("nodestatement fail.", s);
		}
		node.parse(s);		
		return this;
	}
	
	public String toString(){
		return node.toString();
	}
}
