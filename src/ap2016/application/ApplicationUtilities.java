package ap2016.application;


import java.util.Arrays;

import javax.swing.JComponent;


/**
 * This class contains a list of static methods utilities useful in the whole application.
 * @author Matteo Nardini
 *
 */
public class ApplicationUtilities
{

	/**
	 * This methods converts an array of byte to a String, in which every element of the array represent one character of the String.
	 * @param arr The array to be converted to a String.
	 * @return The String generated from the given array.
	 */
	public static String byteArrayToString(byte[] arr)
	{
		return Arrays.toString(arr);
	}

	/**
	 * This method formats a GUI component. If the component is <i>valid</i> then the component is formatted using the {@link ap2016.application.ApplicationConstants#validBorder validBorder} style, otherwise it is formatted with the {@link ap2016.application.ApplicationConstants#invalidBorder invalidBorder}.
	 * @param c The component to be formatted.
	 * @param valid If {@code true} the component is considered to be valid, if {@code false} the component is considered to be invalid.
	 */
	public static void formatComponent(JComponent c, boolean valid)
	{
		if (valid)
		{
			c.setBorder(ApplicationConstants.validBorder);
		} else
		{
			c.setBorder(ApplicationConstants.invalidBorder);
		}
	}

	/**
	 * This methods uses the regular expression defined in {@link ap2016.application.ApplicationConstants#languageRegEx ApplicationConstants} to validate a given language string. An empty language string is considered to be valid.
	 * @param language The language string to be validated.
	 * @return The method returns {@code true} if the given language string is valid, {@code false} otherwise.
	 */
	public static boolean isValidLanguage(String language)
	{
		return ApplicationConstants.languageRegEx.matcher(language).matches() || language.equals("");
	}

	/**
	 * This methods uses the regular expression defined in {@link ap2016.application.ApplicationConstants#urlRegEx ApplicationConstants} to validate a given URL. An empty URL is considered to be valid.
	 * @param url The URL to be validated.
	 * @return The method returns {@code true} if the given URL is valid, {@code false} otherwise.
	 */
	public static boolean isValidURL(String url)
	{
		return ApplicationConstants.urlRegEx.matcher(url).matches() || url.equals("");
	}

	/**
	 * This methods converts a String to an array of byte, in which each byte represents one character of the String. Each character is converted to a byte by using the {@link java.lang.Byte#parseByte(String) Byte.parseByte} method.
	 * @param str The String to be converted.
	 * @return The array of byte in which every byte represents a character of the original string.
	 */
	public static byte[] stringToByteArray(String str)
	{
		// This method practically does the inverse of Arrays.toString(array). However, this method
		// does not works with Strings representing arrays of arrays, because it is not required by
		// this application.
		if (str.equals("[]"))
		{
			return new byte[0];
		}

		String[] el = str.replace("[", "").replace("]", "").replace(" ", "").split(",");
		byte[] ans = new byte[el.length];

		for (int i = 0; i < el.length; i++)
		{
			ans[i] = Byte.parseByte(el[i]);
		}

		return ans;
	}

}
