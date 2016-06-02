package ap2016.exceptions;

/**
 * This class model a runtime exception that should be raised when the supplied avatar name of a user is invalid.
 * @author Matteo Nardini
 *
 */
@SuppressWarnings("serial")
public class InvalidAvatarNameException extends IllegalArgumentException
{
	public InvalidAvatarNameException(String message)
	{
		super(message);
	}
}
