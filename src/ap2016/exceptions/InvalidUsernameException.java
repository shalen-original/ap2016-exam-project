package ap2016.exceptions;

/**
 * This class model a runtime exception that should be raised when the username of a user is invalid.
 * @author Matteo Nardini
 *
 */
@SuppressWarnings("serial")
public class InvalidUsernameException extends IllegalArgumentException
{
	public InvalidUsernameException(String message)
	{
		super(message);
	}
}
