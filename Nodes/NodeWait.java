package Nodes;

import java.util.Scanner;

import Core.*;
import Interfaces.RobotExpressionNode;
import Interfaces.RobotProgramNode;


public class NodeWait implements RobotProgramNode {
	
	private RobotExpressionNode rpn;
	private int x = -1;
	
	@Override
	public void execute(Robot robot) {
		if(rpn == null){
			robot.idleWait();
		}
		else {
			int y = rpn.evaluate(robot);
			for (int i = 0; i < y; i++) {
				robot.idleWait();
			}
		}
	}
	
	@Override
	public RobotProgramNode parse(Scanner s) {
		if(!Parser.gobble(Parser.WAIT, s)){
			Parser.fail("wait fail. expecting: "+Parser.WAIT.toString(), s);
		}
		if(s.hasNext(Parser.OPENBRACE)){
			Parser.gobble(Parser.OPENBRACE, s);
			rpn = new NodeExpression();
			rpn.parse(s);
			if (s.hasNext(Parser.CLOSEBRACE)) {
				Parser.gobble(Parser.CLOSEBRACE, s);
			} else {
				Parser.fail("closebrace fail. expecting: "+Parser.CLOSEBRACE.toString(), s);
			}
		}
		return this;
	}
	
	public String toString(){
		String s = "wait";
		if (rpn != null) s += "(" + x + ")";
		return s;
	}

}
