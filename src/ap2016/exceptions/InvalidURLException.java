package ap2016.exceptions;


/**
 * This class model a runtime exception that should be raised when the supplied URL is invalid.
 * @author Matteo Nardini
 *
 */

public class InvalidURLException extends IllegalArgumentException
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = -5406417848857854929L;

	/**
	 * Throws a new exception with a given message.
	 * @param message The message of this exception.
	 */
	public InvalidURLException(String message)
	{
		super(message);
	}
}
