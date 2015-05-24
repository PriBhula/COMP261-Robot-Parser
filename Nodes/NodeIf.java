package Nodes;
import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.*;


public class NodeIf implements RobotProgramNode {
	private NodeCondition condition = null;
	private RobotProgramNode ifNode = null;
	private RobotProgramNode elseNode = null;

	@Override
	public void execute(Robot robot) {
		if(condition != null){
			if(condition.evaluate(robot)){
				ifNode.execute(robot);
			}
			else if (elseNode != null) {
				elseNode.execute(robot);

			}
		}
	}

	@Override
	public RobotProgramNode parse(Scanner s) {
		if(!Parser.gobble(Parser.IF, s)){
			Parser.fail("if fail. expecting: "+Parser.IF.toString(), s);
		}

		if (!Parser.gobble(Parser.OPENPAREN, s)) {
			Parser.fail("openparen fail. expecting: " + Parser.OPENPAREN.toString(), s);
		}

		condition = new NodeCondition();
		condition.parse(s);

		if (!Parser.gobble(Parser.CLOSEPAREN, s)) {
			Parser.fail("closeparen fail. expecting: " + Parser.CLOSEPAREN.toString(), s);
		}

		ifNode = new NodeBlock();
		ifNode.parse(s);

		if (s.hasNext(Parser.ELSE)) {
			if (!Parser.gobble(Parser.ELSE, s)) {
				Parser.fail("else fail. expecting " + Parser.ELSE.toString(), s);
			}

			elseNode = new NodeBlock();
			elseNode.parse(s);
		}

		return this;
	}

	public String toString(){
		String s = "if(" + ifNode.toString() + ") " + ifNode.toString();
		if (elseNode != null) {
			s += "else " + elseNode.toString();
		}
		return s;
		}
}
