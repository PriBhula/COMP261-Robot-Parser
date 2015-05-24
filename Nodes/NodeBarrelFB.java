package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;

public class NodeBarrelFB implements RobotExpressionNode {
	public RobotExpressionNode node; //= null;

	@Override
	public int evaluate(Robot robot) {
		return robot.getBarrelFB(node.evaluate(robot));
	}

	@Override
	public RobotExpressionNode parse(Scanner s) {
		if(!Parser.gobble(Parser.BARRELFB, s)){
			Parser.fail("barrelfb fail. expecting" +Parser.BARRELFB.toString(), s);
		}
		if(s.hasNext(Parser.OPENPAREN)){
			Parser.gobble(Parser.OPENPAREN, s);
			node = new NodeExpression();
			node.parse(s);
			
			if(s.hasNext(Parser.CLOSEPAREN)){
				Parser.gobble(Parser.CLOSEPAREN, s);
			}
			else{
				Parser.fail("closeparen fail. expecting: ", s);
			}
		}
		return this;
	}
	
	public String toString(){
		return Parser.BARRELFB.toString();
	}

}
