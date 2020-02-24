package includes;

import java.lang.Exception;
public class ErrorException extends Exception{
	String error_msg;
	public ErrorException(){}
	public ErrorException(String msg){error_msg=msg;}
	public String getMessage(){return error_msg;}
}//end ErrorException class