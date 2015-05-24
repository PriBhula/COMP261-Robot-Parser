package Core;
public class ParserFailureException extends RuntimeException{
	public ParserFailureException(String msg){
		super(msg);
		//System.out.println("FAILURE EXCEPTION");
	}
}
