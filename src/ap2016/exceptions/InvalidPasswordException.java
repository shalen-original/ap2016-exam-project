package ap2016.exceptions;

/**
 * This class model a runtime exception that should be raised when the password for a user is invalid.
 * @author Matteo Nardini
 *
 */
@SuppressWarnings("serial")
public class InvalidPasswordException extends IllegalArgumentException
{
	public InvalidPasswordException(String message)
	{
		super(message);
	}
}
