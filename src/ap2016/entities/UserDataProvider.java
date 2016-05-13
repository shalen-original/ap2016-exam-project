package ap2016.entities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ap2016.application.ApplicationConstants;
import ap2016.application.ApplicationUtilities;

public class UserDataProvider {
	
	private ArrayList<User> users;
	private String userFilename = "users.xml";
	
	
	private static UserDataProvider instance;
	public static UserDataProvider getInstance()
	{
		if (instance == null)
		{
			instance = new UserDataProvider();
		}
		
		return instance;
	}
	
	
	private UserDataProvider()
	{
		users = new ArrayList<User>();
	}
	
	
	public ArrayList<User> getUsers()
	{
		return users;
	}
	
	
	
	
	public void readDataFromFile() throws Exception
	{
		users.clear();
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(ApplicationConstants.dataBase + "\\" + userFilename));
			
			NodeList userNodes = doc.getElementsByTagName("user");
			NodeList current;
			NodeList currentRoles;
			User currentUser;
			
			String username, avatarName;
			byte[] pwd, salt;
			
			for (int i = 0; i < userNodes.getLength(); i++)
			{
				current = userNodes.item(i).getChildNodes();
				username = current.item(0).toString();
				pwd = ApplicationUtilities.stringToByteArray(current.item(1).toString());
				salt = ApplicationUtilities.stringToByteArray(current.item(2).toString());
				avatarName = current.item(3).toString();
				
				currentUser = new User(username, pwd, salt, avatarName);
				
				currentRoles = current.item(4).getChildNodes();
				for(int j = 0; j < currentRoles.getLength(); j++)
				{
					currentUser.grantRole(Role.valueOf(currentRoles.item(j).toString()));
				}
				
				users.add(currentUser);	
			}
			
			
		} catch (IOException | ParserConfigurationException e) {
			throw new Exception("The username file located at \"" + ApplicationConstants.dataBase + "\\" + userFilename + "\" is not valid");
		} catch (SAXException e) {}
		
		
	}
	
	
	
	
	
	public void saveDataToFile() throws TransformerFactoryConfigurationError, TransformerException
	{
		Document doc = null;
		try
		{
			// Starts building the new document
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			
			// Creates the root elements and appends it to the document
			Element uRoot = doc.createElement("users");
			doc.appendChild(uRoot);
			
			Element currUserNode = null;
			Element currUserRoles = null;
			for (User u : users)
			{
				// Creates this user
				currUserNode = doc.createElement("user");
				
				// Adds the name
				addElementWithText(currUserNode, "username", u.getUsername(), doc);
				
				// Saves the password
				addElementWithText(currUserNode, "password", ApplicationUtilities.byteArrayToString(u.getCurrentPasswordHash()), doc);
				
				// Saves the salt
				addElementWithText(currUserNode, "salt", ApplicationUtilities.byteArrayToString(u.getCurrentPasswordSalt()), doc);
				
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
			
		} catch (ParserConfigurationException e) {}
		

		Transformer t = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(ApplicationConstants.dataBase + "\\" + userFilename));
		t.transform(source, result);
	}
	
	
	
	
	private void addElementWithText(Element root, String elementName, String elementText, Document doc)
	{
		Element tmp = doc.createElement(elementName);
		tmp.appendChild(doc.createTextNode(elementText));
		root.appendChild(tmp);
	}

}
