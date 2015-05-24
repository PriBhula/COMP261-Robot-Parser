package Nodes;
import java.util.Scanner;

import Core.*;
import Interfaces.RobotProgramNode;


public class NodeAction implements RobotProgramNode {
	private RobotProgramNode node = null;
	
	@Override
	public void execute(Robot robot) {
		node.execute(robot);
	}

	@Override
	public RobotProgramNode parse(Scanner s) {
		if (s.hasNext(Parser.MOVE)) {
			//System.out.println("MOVE");
			node = new NodeMove();
		} 
		else if (s.hasNext(Parser.TAKEFUEL)) {
			//System.out.println("TAKEFUEL");
			node = new NodeTakeFuel();
		} 
		else if (s.hasNext(Parser.TURNL)) {
			//System.out.println("TURNL");
			node = new NodeTurnL();
		} 
		else if (s.hasNext(Parser.TURNR)) {
			//System.out.println("TURNR");
			node = new NodeTurnR();
		} 
		else if (s.hasNext(Parser.WAIT)) {
			//System.out.println("WAIT");
			node = new NodeWait();
		} 
		else if (s.hasNext(Parser.TURNAROUND)) {
			//System.out.println("TURNA");
			node = new NodeTurnA();
		}
		else if (s.hasNext(Parser.SHIELDON)) {
			node = new NodeShieldOn();
		} 
		else if (s.hasNext(Parser.SHIELDOFF)) {
			node = new NodeShieldOff();
		}
		else{
			Parser.fail("nodeaction fail", s);
		}
		node.parse(s);
		
		if (!Parser.gobble(Parser.SEMICOL, s)) {
			Parser.fail("Expecting ;", s);
		}
		
		return node;
	}
	
	public String toString(){
		return (node.toString());
	}

}
