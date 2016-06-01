package ap2016.exceptions;

@SuppressWarnings("serial")
public class InvalidUsernameException extends IllegalArgumentException
{
	public InvalidUsernameException(String message)
	{
		super(message);
	}
}
