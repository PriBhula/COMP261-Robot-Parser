package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;

public class NodeNumber implements RobotExpressionNode {
	
	public int number;
	
	public NodeNumber(int n){
		this.number = n;
	}
	
	public NodeNumber(){
		this.number = 0;
	}
	
	@Override
	public int evaluate(Robot robot) {
		return number;
	}

	@Override
	public RobotExpressionNode parse(Scanner s) {
		if (s.hasNext(Parser.NUM)) {
			String value = s.next(Parser.NUM);
			number = Integer.parseInt(value);
		} else {
			Parser.fail("number fail. expecting: " + Parser.NUM.toString(), s);
		}
		return this;
	}
	
	public String toString() {
		return Integer.toString(number);
	}

}
