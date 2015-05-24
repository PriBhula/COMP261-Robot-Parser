package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotConditionNode;

public class NodeAnd implements RobotConditionNode {
	NodeCondition nodeA = null;
	NodeCondition nodeB = null;
	
	@Override
	public boolean evaluate(Robot robot) {
		if (nodeA.evaluate(robot) && nodeB.evaluate(robot)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public RobotConditionNode parse(Scanner s) {
		if(!Parser.gobble(Parser.AND, s)){
			Parser.fail("and fail. expecting: "+Parser.AND.toString(), s);
		}
		
		if (!Parser.gobble(Parser.OPENPAREN, s)) {
			Parser.fail("openparen fail. expecting : " + Parser.OPENPAREN.toString(), s);
		}

		
		nodeA = new NodeCondition();
		nodeA.parse(s);

		if (!Parser.gobble(Parser.COMMA, s)) {
			Parser.fail("comma fail. expecting ,", s);
		}

		nodeB = new NodeCondition();
		nodeB.parse(s);

		if (!Parser.gobble(Parser.CLOSEPAREN, s)) {
			Parser.fail("closeparen fail. expecting: "+ Parser.CLOSEPAREN.toString(), s);
		}
		return this;
	}
	
	public String toString() {
		return "and("+nodeA.toString()+", "+nodeB.toString()+")";
	}

}
