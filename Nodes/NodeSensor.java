package Nodes;

import java.util.Scanner;

import Core.Parser;
import Core.Robot;
import Interfaces.RobotExpressionNode;
import Interfaces.RobotProgramNode;

public class NodeSensor implements RobotExpressionNode {
	
	RobotExpressionNode node = null;

	@Override
	public int evaluate(Robot robot) {
		return node.evaluate(robot);
	}

	@Override
	public RobotExpressionNode parse(Scanner s) {
		if (s.hasNext(Parser.FUELLEFT)){
			node = new NodeFuelLeft();
		} else if (s.hasNext(Parser.OPPLR)){
			node = new NodeOpponentLR();
		} else if (s.hasNext(Parser.OPPFB)){
			node = new NodeOpponentFB();
		} else if (s.hasNext(Parser.NUMBARRELS)){
			node = new NodeNumBarrels();
		} else if (s.hasNext(Parser.BARRELLR)){
			node = new NodeBarrelLR();
		} else if (s.hasNext(Parser.BARRELFB)){
			node = new NodeBarrelFB();
		} else if (s.hasNext(Parser.WALLDIST)){
			node = new NodeWallDist();
		}
		node.parse(s);
		return node;
	}
	
	public String toString(){
		return node.toString();
	}

}
