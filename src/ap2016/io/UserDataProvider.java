package ap2016.io;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ap2016.application.ApplicationUtilities;
import ap2016.entities.Role;
import ap2016.entities.User;


/**
 * This class acts as a wrapper between the <i>user.xml</i> file and the rest of the application. Is uses the <i>Singleton</i> pattern.
 * @author Matteo Nardini
 *
 */
public class UserDataProvider extends DataProvider<User>
{

	/**
	 * Contains the unique instance of the class.
	 */
	private static UserDataProvider instance;

	/**
	 * Returns the instance of this class.
	 * @return This method returns the instance of this class.
	 */
	public static UserDataProvider getInstance()
	{
		if (instance == null)
		{
			instance = new UserDataProvider();
		}

		return instance;
	}

	/**
	 * Creates a new user data provider from the file <i>user.xml</i>
	 */
	private UserDataProvider()
	{
		super("user.xml");
	}

	/**
	 * This application doesn't allow to import user file. This method doesn't do anything.
	 */
	@Override
	protected void appendDoc(Document doc)
	{
		// This application doesn't allow to import user file.
	}

	/**
	 * Converts the list of {@link ap2016.entities.User User} to an XML document.
	 */
	@Override
	protected void buildDoc(Document doc)
	{
		// Creates the root elements and appends it to the document
		Element uRoot = doc.createElement("users");
		doc.appendChild(uRoot);

		Element currUserNode = null;
		Element currUserRoles = null;
		for (User u : this.data)
		{
			// Creates this user
			currUserNode = doc.createElement("user");

			// Adds the name
			addElementWithText(currUserNode, "username", u.getUsername(), doc);

			// Saves the password
			addElementWithText(currUserNode, "password",
					ApplicationUtilities.byteArrayToString(u.getCurrentPasswordHash()), doc);

			// Saves the salt
			addElementWithText(currUserNode, "salt", ApplicationUtilities.byteArrayToString(u.getCurrentPasswordSalt()),
					doc);

			// Saves the password
			addElementWithText(currUserNode, "avatar", u.getAvatarName(), doc);

			// Saves the roles
			currUserRoles = doc.createElement("roles");

			for (String r : u.getRolesString())
			{
				addElementWithText(currUserRoles, "role", r, doc);
			}

			currUserNode.appendChild(currUserRoles);
			uRoot.appendChild(currUserNode);

		}
	}

	/**
	 * Converts the file <i>user.xml</i> to a list of {@link ap2016.entities.User User}.
	 */
	@Override
	protected void parseDoc(Document doc)
	{
		NodeList userNodes = doc.getElementsByTagName("user");
		NodeList current;
		NodeList currentRoles;
		User currentUser;

		String username, avatarName;
		byte[] pwd, salt;

		for (int i = 0; i < userNodes.getLength(); i++)
		{
			// Gets this user's data
			current = userNodes.item(i).getChildNodes();

			// Gets the name
			username = current.item(0).getTextContent();

			// The password and its salt
			pwd = ApplicationUtilities.stringToByteArray(current.item(1).getTextContent());
			salt = ApplicationUtilities.stringToByteArray(current.item(2).getTextContent());

			// The avatar name
			avatarName = current.item(3).getTextContent();

			// Creates the new user
			currentUser = new User(username, pwd, salt, avatarName);

			// Adds all the roles to this user
			currentRoles = current.item(4).getChildNodes();
			for (int j = 0; j < currentRoles.getLength(); j++)
			{
				currentUser.grantRole(Role.valueOf(currentRoles.item(j).getTextContent()));
			}

			this.data.add(currentUser);
		}
	}

}
