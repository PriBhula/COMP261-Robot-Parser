package Nodes;
import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotConditionNode;
import Interfaces.RobotProgramNode;


public class NodeCondition implements RobotConditionNode{
	private RobotConditionNode node = null;
	
	public boolean evaluate(Robot robot) {
		return node.evaluate(robot);
	}

	@Override
	public RobotConditionNode parse(Scanner s) {
		if (s.hasNext(Parser.GREATERTHEN)) {
			node = new NodeGreaterThan();
		} 
		else if (s.hasNext(Parser.LESSTHEN)) {
			node = new NodeLessThan();
		} 
		else if (s.hasNext(Parser.EQUAL)) {
			node = new NodeEqualTo();
		}
		node.parse(s);
		return node;
	}
	
	public String toString(){
		return node.toString();
	}

}
