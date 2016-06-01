package ap2016.exceptions;

@SuppressWarnings("serial")
public class InvalidURLException extends IllegalArgumentException
{
	public InvalidURLException(String message)
	{
		super(message);
	}
}
