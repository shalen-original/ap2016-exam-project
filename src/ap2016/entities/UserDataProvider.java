package ap2016.entities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
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
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(ApplicationConstants.dataBase + "\\" + userFilename));
			
			NodeList userNodes = doc.getElementsByTagName("user");
			NodeList current;
			User currentUser;
			
			String username, avatarName;
			byte[] pwd, salt;
			
			for (int i = 0; i < userNodes.getLength(); i++)
			{
				current = userNodes.item(i).getChildNodes();
				username = current.item(0).toString();
				pwd = ApplicationUtilities.stringToByteArray(current.item(1).toString());
				salt = ApplicationUtilities.stringToByteArray(current.item(1).toString());
				avatarName = current.item(2).toString();
				
				currentUser = new User(username, pwd, salt, avatarName);
				
				// Add roles
				
				users.add(currentUser);	
			}
			
			
		} catch (IOException | ParserConfigurationException e) {
			throw new Exception("The username file located at \"" + ApplicationConstants.dataBase + "\\" + userFilename + "\" is not valid");
		} catch (SAXException e) {}
		
		
	}
	
	
	
	
	
	public void saveDataFromFile()
	{
		
	}

}
