package ap2016.exceptions;


/**
 * This class model a runtime exception that should be raised when the supplied language string is invalid.
 * @author Matteo Nardini
 *
 */
public class InvalidLanguageStringException extends IllegalArgumentException
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = -1647531615489767745L;

	/**
	 * Throws a new exception with a given message.
	 * @param message The message of this exception.
	 */
	public InvalidLanguageStringException(String message)
	{
		super(message);
	}
}
