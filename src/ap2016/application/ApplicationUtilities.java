package ap2016.application;

import java.util.Arrays;

import javax.swing.JComponent;

public class ApplicationUtilities {

	public static boolean isValidURL(String url) {
		return ApplicationConstants.urlRegEx.matcher(url).matches();
	}
	
	public static boolean isValidLanguage(String language) {
		return ApplicationConstants.languageRegEx.matcher(language).matches();
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
	
	public static void formatComponent(JComponent c, boolean valid)
	{
		if (valid)
		{
			c.setBorder(ApplicationConstants.validBorder);
		}else{
			c.setBorder(ApplicationConstants.invalidBorder);
		}
	}
	
}
