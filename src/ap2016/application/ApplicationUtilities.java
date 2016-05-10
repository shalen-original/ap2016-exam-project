package ap2016.application;

public class ApplicationUtilities {

	public static boolean isValidURL(String url) {
		return ApplicationConstants.urlRegEx.matcher(url).matches();
	}
	
	public static byte[] stringToByteArray(String str)
	{
		return null;
	}
	
}
