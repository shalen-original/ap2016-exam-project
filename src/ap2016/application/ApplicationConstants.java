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
	public final static Pattern usernameRegEx = Pattern.compile("");
	
	/**
	 * The regular expression used to validate passwords
	 */
	public final static Pattern passwordRegEx = Pattern.compile("");
	
	
	
	
	/**
	 * The folder containing all the assets (relative path)
	 */
	public final static String assetsBase = "assets/";
	
	/**
	 * The folder containing all the XML data (relative path)
	 */
	public final static String dataBase = "data/";
	
	
	
}
