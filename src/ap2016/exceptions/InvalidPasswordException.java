package ap2016.exceptions;


/**
 * This class model a runtime exception that should be raised when the password for a user is invalid.
 * @author Matteo Nardini
 *
 */

public class InvalidPasswordException extends IllegalArgumentException
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = -7105252579838926925L;

	/**
	 * Throws a new exception with a given message.
	 * @param message The message of this exception.
	 */
	public InvalidPasswordException(String message)
	{
		super(message);
	}
}
