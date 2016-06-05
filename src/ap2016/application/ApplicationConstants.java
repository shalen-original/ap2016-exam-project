package ap2016.application;


import java.awt.Color;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.border.Border;


/**
 * Useful application-wide constants.
 * @author Matteo Nardini
 *
 */
public class ApplicationConstants
{

	/**
	 * The regular expression used to validate usernames.
	 */
	public final static Pattern usernameRegEx = Pattern.compile("([a-zA-Z0-9_,'-]*)([.]?)([a-zA-Z0-9_,'-]*)");

	/**
	 * The regular expression used to validate passwords.
	 */
	public final static Pattern passwordRegEx = Pattern.compile(".{8,}");

	/**
	 * The regular expression used to validate URLs. The following regular expression has been kindly provided by the website: http://stackoverflow.com/questions/163360/regular-expression-to-match-urls-in-java. The regex is not perfect, but it detects correctly most of the URLs. It could be the case that a valid URL is discarded by this RegEx.
	 */
	public final static Pattern urlRegEx = Pattern
			.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

	/**
	 * The regular expression used to validate the "language" strings. A language string is expected to be in the format "en-EN", with the two letter before the dash being the minor locale and the to after being the major locale.
	 */
	public final static Pattern languageRegEx = Pattern.compile("[a-z]{2}(-[A-Z]{2})?");

	/**
	 * The folder containing all the assets (relative path).
	 */
	public final static String assetsBase = Paths
			.get(getBaseFolder(), "assets", (System.getenv("TEST_FOLDER") != null ? System.getenv("TEST_FOLDER") : ""))
			.toAbsolutePath().toString();

	/**
	 * The folder containing all the XML data (relative path).
	 */
	public final static String dataBase = Paths
			.get(getBaseFolder(), "data", (System.getenv("TEST_FOLDER") != null ? System.getenv("TEST_FOLDER") : ""))
			.toAbsolutePath().toString();

	/**
	 * The color used in the program to represent a "valid" situation.
	 */
	public final static Color validColor = new Color(185, 212, 112);

	/**
	 * The color used in the program to represent an "invalid" situation.
	 */
	public final static Color invalidColor = new Color(236, 160, 150);

	/**
	 * The border used in the program to represent a valid input in a component.
	 */
	public final static Border validBorder = BorderFactory.createLineBorder(validColor, 2, true);

	/**
	 * The border used in the program to represent an invalid input in a component.
	 */
	public final static Border invalidBorder = BorderFactory.createLineBorder(invalidColor, 2, true);

	/**
	 * Allows to obtain the absolute path string of the starting folder of the application.
	 * @return The string that contains the absolute path of the starting folder of the application.
	 */
	private static String getBaseFolder()
	{
		try
		{
			return Paths.get(ApplicationConstants.class.getClassLoader().getResource("").toURI()).toString();
		} catch (URISyntaxException e)
		{
			return "";
		}
	}

}
