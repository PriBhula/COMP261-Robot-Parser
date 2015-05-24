package Core;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;

import javax.swing.JFileChooser;

import Interfaces.*;
import Nodes.*;

/** The parser and interpreter.
    The top level parse function, a main method for testing, and several
    utility methods are provided.
    You need to implement parseProgram and all the rest of the parser.
 */

public class Parser {

	/**
	 * Top level parse method, called by the World
	 */
	static RobotProgramNode parseFile(File code){
		//System.out.println("parsing file");
		Scanner scan = null;
		try {
			scan = new Scanner(code);
			//while(scan.hasNext()){System.out.println(scan.next());}

			// the only time tokens can be next to each other is
			// when one of them is one of (){},;
			scan.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");

			RobotProgramNode n = parseProgram(scan);  // You need to implement this!!!

			scan.close();
			return n;
		} catch (FileNotFoundException e) {
			System.out.println("Robot program source file not found");
		} catch (ParserFailureException e) {
			System.out.println("Parser error:");
			System.out.println(e.getMessage());
			scan.close();
		}
		return null;
	}

	/** For testing the parser without requiring the world */

	public static void main(String[] args){
		if (args.length>0){
			//System.out.println(">0");
			for (String arg : args){
				File f = new File(arg);
				System.out.println("file: "+f.getName());
				if (f.exists()){
					System.out.println("Parsing '"+ f+"'");
					RobotProgramNode prog = parseFile(f);	
					System.out.println("Parsing completed ");
					if (prog!=null){
						System.out.println("================\nProgram:");
						System.out.println(prog);}
					System.out.println("=================");
				}
				else {System.out.println("Can't find file '"+f+"'");}
			}
		} else {
			while (true){
				JFileChooser chooser = new JFileChooser(".");//System.getProperty("user.dir"));
				int res = chooser.showOpenDialog(null);
				if(res != JFileChooser.APPROVE_OPTION){ break;}
				RobotProgramNode prog = parseFile(chooser.getSelectedFile());
				System.out.println("Parsing completed");
				if (prog!=null){
					System.out.println("Program: \n"+prog);
				}
				System.out.println("=================");
			}
		}
		System.out.println("Done");
	}

	// Useful Patterns

	public static Pattern NUMPAT = Pattern.compile("-?\\d+");  //("-?(0|[1-9][0-9]*)");
	public static Pattern OPENPAREN = Pattern.compile("\\(");
	public static Pattern CLOSEPAREN = Pattern.compile("\\)");
	public static Pattern OPENBRACE = Pattern.compile("\\{");
	public static Pattern CLOSEBRACE = Pattern.compile("\\}");
	public static Pattern SEMICOL = Pattern.compile(";");
	public static Pattern COMMA = Pattern.compile(",");
	
	public static Pattern ACTION = Pattern.compile("move|takeFuel|turnL|turnR|turnAround|wait|shieldOn|shieldOff");

	public static Pattern MOVE = Pattern.compile("move");
	public static Pattern TAKEFUEL = Pattern.compile("takeFuel");
	public static Pattern TURNL = Pattern.compile("turnL");
	public static Pattern TURNR = Pattern.compile("turnR");
	public static Pattern WAIT = Pattern.compile("wait");
	public static Pattern TURNAROUND = Pattern.compile("turnAround");
	public static Pattern SHIELDON = Pattern.compile("shieldOn");
	public static Pattern SHIELDOFF = Pattern.compile("shieldOff");
	
	public static Pattern CONDITION = Pattern.compile("and|or|not|lt|gt|eq");
	
	public static Pattern LESSTHEN = Pattern.compile("lt");
	public static Pattern GREATERTHEN = Pattern.compile("gt");
	public static Pattern EQUAL = Pattern.compile("eq");
	public static Pattern AND = Pattern.compile("and");
	public static Pattern NOT = Pattern.compile("not");
	
	public static Pattern SENSOR = Pattern.compile("fuelLeft|oppLR|oppFB|numBarrels|barrelLR|barrelFB|wallDist");
	
	public static Pattern FUELLEFT = Pattern.compile("fuelLeft");
	public static Pattern OPPLR = Pattern.compile("oppLR");
	public static Pattern OPPFB = Pattern.compile("oppFB");
	public static Pattern NUMBARRELS = Pattern.compile("numBarrels");
	public static Pattern BARRELLR = Pattern.compile("barrelLR");
	public static Pattern BARRELFB = Pattern.compile("barrelFB");
	public static Pattern WALLDIST = Pattern.compile("wallDist");

	public static Pattern LOOP = Pattern.compile("loop");
	public static Pattern IF = Pattern.compile("if");
	public static Pattern ELSE = Pattern.compile("else");
	public static Pattern WHILE = Pattern.compile("while");
	
	public static Pattern NUM = Pattern.compile("-?[0-9]+");
	
	public static Pattern OPERATION = Pattern.compile("add|sub|mul|div");
	
	public static Pattern ADD = Pattern.compile("add");
	public static Pattern SUB = Pattern.compile("sub");
	public static Pattern MUL = Pattern.compile("mul");
	public static Pattern DIV = Pattern.compile("div");
	
	
	//PROG  ::= STMT+

	static RobotProgramNode parseProgram(Scanner s){
		//System.out.println("parsing prog");
		RobotProgramNode node = null;
		if (s != null){
			node = new NodeProgram().parse(s);
		}
		return node;
		// just so it will compile!!
	}


	//utility methods for the parser
	/**
	 * Report a failure in the parser.
	 */
	public static void fail(String message, Scanner s){
		String msg = message + "\n   @ ...";
		for (int i=0; i<5 && s.hasNext(); i++){
			msg += " " + s.next();
		}
		throw new ParserFailureException(msg+"...");
	}

	/**
       If the next token in the scanner matches the specified pattern,
       consume the token and return true. Otherwise return false without
       consuming anything.
       Useful for dealing with the syntactic elements of the language
       which do not have semantic content, and are there only to
       make the language parsable.
	 */
	public static boolean gobble(String p, Scanner s){
		if (s.hasNext(p)) { s.next(); return true;} 
		else { return false; } 
	}
	public static boolean gobble(Pattern p, Scanner s){
		if (s.hasNext(p)) { s.next(); return true;} 
		else { return false; } 
	}


}

// You could add the node classes here, as long as they are not declared public (or private)
