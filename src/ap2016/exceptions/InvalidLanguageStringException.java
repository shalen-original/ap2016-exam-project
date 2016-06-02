package ap2016.exceptions;

/**
 * This class model a runtime exception that should be raised when the supplied language string is invalid.
 * @author Matteo Nardini
 *
 */
@SuppressWarnings("serial")
public class InvalidLanguageStringException extends IllegalArgumentException
{
	public InvalidLanguageStringException(String message)
	{
		super(message);
	}
}
