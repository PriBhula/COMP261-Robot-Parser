package Interfaces;

import java.util.Scanner;

import Core.Robot;

public interface RobotExpressionNode {
	
	public int evaluate(Robot robot);
	public RobotExpressionNode parse (Scanner s);
}
