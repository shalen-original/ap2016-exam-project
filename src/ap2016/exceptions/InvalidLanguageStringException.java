package ap2016.exceptions;

@SuppressWarnings("serial")
public class InvalidLanguageStringException extends IllegalArgumentException
{
	public InvalidLanguageStringException(String message)
	{
		super(message);
	}
}
