package ap2016.exceptions;


/**
 * This class model a runtime exception that should be raised when the supplied avatar name of a user is invalid.
 * @author Matteo Nardini
 *
 */
public class InvalidAvatarNameException extends IllegalArgumentException
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = 4340987847646455685L;

	/**
	 * Throws a new exception with a given message.
	 * @param message The message of this exception.
	 */
	public InvalidAvatarNameException(String message)
	{
		super(message);
	}
}
