package ap2016.application;

import java.util.Arrays;

public class ApplicationUtilities {

	public static boolean isValidURL(String url) {
		return ApplicationConstants.urlRegEx.matcher(url).matches();
	}
	
	public static byte[] stringToByteArray(String str)
	{
		if (str.equals("[]"))
			return new byte[0];
		
		String[] el = str.replace("[", "").replace("]", "").replace(" ", "").split(",");
		byte[] ans = new byte[el.length];
		
		for (int i = 0; i < el.length; i++)
		{
			ans[i] = Byte.parseByte(el[i]);
		}
		
		return ans;
	}
	
	public static String byteArrayToString(byte[] arr)
	{
		return Arrays.toString(arr);
	}
	
}
