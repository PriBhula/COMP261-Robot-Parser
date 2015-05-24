package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotConditionNode;

public class NodeNot implements RobotConditionNode {
	NodeCondition node = null;
	
	@Override
	public boolean evaluate(Robot robot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RobotConditionNode parse(Scanner s) {
		if(!Parser.gobble(Parser.NOT, s)){
			Parser.fail("not fail. expecting: "+Parser.NOT.toString(), s);
		}
		
		if (!Parser.gobble(Parser.OPENPAREN, s)) {
			Parser.fail("openparen fail. expecting : " + Parser.OPENPAREN.toString(), s);
		}

		node = new NodeCondition();
		node.parse(s);

		if (!Parser.gobble(Parser.CLOSEPAREN, s)) {
			Parser.fail("closeparen fail. expecting: "+ Parser.CLOSEPAREN.toString(), s);
		}
		return this;
	}
	
	public String toString() {
		return "not("+node.toString()+")";
	}

}
