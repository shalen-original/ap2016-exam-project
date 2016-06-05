package ap2016.exceptions;


/**
 * This class model a runtime exception that should be raised when the username of a user is invalid.
 * @author Matteo Nardini
 *
 */

public class InvalidUsernameException extends IllegalArgumentException
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = 9123893104703186010L;

	/**
	 * Throws a new exception with a given message.
	 * @param message The message of this exception.
	 */
	public InvalidUsernameException(String message)
	{
		super(message);
	}
}
