/**
 * Exception for invalid values
 * @author Kieran Raftery
 *
 */
public class ValueNotValidException extends RuntimeException
{
	/**
	 * Default constructor for ValueNotValidException
	 */
	public ValueNotValidException() 
	{

	}
	/**
	 * Constructor for ValueNotValidException
	 * @param msg the message displayed
	 */
	public ValueNotValidException(String msg) 
	{
		super(msg);
	}
}
