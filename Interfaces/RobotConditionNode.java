package Interfaces;

import java.util.Scanner;

import Core.Robot;

public interface RobotConditionNode {

	public boolean evaluate(Robot robot);

	public RobotConditionNode parse(Scanner scan);
		
}
