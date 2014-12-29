/**
 * Exception for inputs out of range
 * @author Kieran Raftery
 *
 */
public class InputOutOfRangeException extends RuntimeException
{
	/**
	 * Default constructor for InputOutOfRangeException
	 */
	public InputOutOfRangeException() 
	{

	}
	/**
	 * Constructor for InputOutOfRangeException
	 * @param msg the message displayed
	 */
	public InputOutOfRangeException(String msg) 
	{
		super(msg);
	}
}
