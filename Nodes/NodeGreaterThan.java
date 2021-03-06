package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotConditionNode;
import Interfaces.RobotExpressionNode;
import Interfaces.RobotProgramNode;

public class NodeGreaterThan implements RobotConditionNode {
	public RobotExpressionNode nodeA = null;
	public RobotExpressionNode nodeB = null;
	
	@Override
	public boolean evaluate(Robot robot) {
		if(nodeA.evaluate(robot)>nodeB.evaluate(robot)){
			return true;
		}
		return false;
	}

	@Override
	public RobotConditionNode parse(Scanner s) {
		if(!Parser.gobble(Parser.GREATERTHEN, s)){
			Parser.fail("greaterthan fail. expecting: "+Parser.GREATERTHEN.toString(), s);
		}
		if(!Parser.gobble(Parser.OPENPAREN, s)){
			Parser.fail("openparen fail. expecting: "+Parser.OPENPAREN.toString(), s);
		}
		nodeA = new NodeExpression();
		nodeA.parse(s);
		
		if (!Parser.gobble(Parser.COMMA,s)){
			Parser.fail("comma fail. expecting: "+Parser.COMMA.toString(), s);
		}
		
		nodeB = new NodeExpression();
		nodeB.parse(s);
		
		if (!Parser.gobble(Parser.CLOSEPAREN, s)) {
			Parser.fail("closeparen fail. expecting: " + Parser.CLOSEPAREN.toString(), s);
		}
		return this;
		
	}
	
	public String toString(){
		return "gt(" + nodeA.toString() + ", " + nodeB.toString() + ")";
	
	}

}
