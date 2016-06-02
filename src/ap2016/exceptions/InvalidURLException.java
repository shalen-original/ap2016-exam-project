package ap2016.exceptions;

/**
 * This class model a runtime exception that should be raised when the supplied URL is invalid.
 * @author Matteo Nardini
 *
 */
@SuppressWarnings("serial")
public class InvalidURLException extends IllegalArgumentException
{
	public InvalidURLException(String message)
	{
		super(message);
	}
}
