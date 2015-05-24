package Nodes;
import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotProgramNode;


public class NodeWhile implements RobotProgramNode {
	public NodeCondition nodeC;
	public NodeBlock nodeB;

	@Override
	public void execute(Robot robot) {
		while(true){
			if(nodeC.evaluate(robot)){
				nodeB.execute(robot);
			}
			else{
				return;
			}
		}
	}

	@Override
	public RobotProgramNode parse(Scanner s) {
		if (!Parser.gobble(Parser.WHILE, s)) {
			Parser.fail("while fail. expecting: " + Parser.WHILE.toString(), s);
		}

		if (s.hasNext(Parser.OPENPAREN)) {
			Parser.gobble(Parser.OPENPAREN, s);

			// "COND"
			if (s.hasNext(Parser.CONDITION)) {
				nodeC = new NodeCondition();
				nodeC.parse(s);
			} else {
				Parser.fail("condition fail. expecting: " + Parser.CONDITION, s);
			}

			// ")"
			if (s.hasNext(Parser.CLOSEPAREN)) {
				Parser.gobble(Parser.CLOSEPAREN, s);
			}

			nodeB = new NodeBlock();
			nodeB.parse(s);
		}
		return this;

	}

	public String toString(){
		return "while(" + nodeC.toString() + ") " + nodeB.toString();
	}

}
