package ap2016.exceptions;

@SuppressWarnings("serial")
public class InvalidAvatarNameException extends IllegalArgumentException
{
	public InvalidAvatarNameException(String message)
	{
		super(message);
	}
}
