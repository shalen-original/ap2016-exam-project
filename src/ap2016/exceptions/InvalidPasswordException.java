package ap2016.exceptions;

@SuppressWarnings("serial")
public class InvalidPasswordException extends IllegalArgumentException
{
	public InvalidPasswordException(String message)
	{
		super(message);
	}
}
