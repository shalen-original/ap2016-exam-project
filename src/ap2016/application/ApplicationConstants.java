package ap2016.application;

import java.util.regex.Pattern;

/**
 * Useful application-wide constants.
 * @author Matteo Nardini
 *
 */
public class ApplicationConstants {

	/**
	 * The regular expression used to validate usernames
	 */
	public final static Pattern usernameRegEx = Pattern.compile("([a-zA-Z0-9_,'-]*)([.]?)([a-zA-Z0-9_,'-]*)");
	
	/**
	 * The regular expression used to validate passwords
	 */
	public final static Pattern passwordRegEx = Pattern.compile(".{8,}");
	
	/**
	 * The regular expression used to validate URLs. The following regular expression has been kindly provided
	 * by the website: http://stackoverflow.com/questions/163360/regular-expression-to-match-urls-in-java
	 * The regex is not perfect, but it detects correctly most of the URLs. It could be the case that
	 * a valid URL is discarded by this RegEx.
	 */
	public final static Pattern urlRegEx = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
	
	
	
	
	/**
	 * The folder containing all the assets (relative path)
	 */
	public final static String assetsBase = "assets\\" + System.getenv("TEST_FOLDER");
	
	/**
	 * The folder containing all the XML data (relative path)
	 */
	public final static String dataBase = "data\\" + System.getenv("TEST_FOLDER");
	
	
	
	
	
	
}
