package ap2016.entities;

import java.awt.Image;
import java.util.ArrayList;

import ap2016.application.ApplicationConstants;

public class User {
	
	private String username;
	private byte[] passwordHash;
	
	private Image avatar;
	private String avatarName;
	
	private ArrayList<Role> roles;

			
	
	
	
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{	
		if (isValidUsername(username))
		{
			this.username = username;
		}else{
			throw new IllegalArgumentException("The username \"" + username + "\" is not valid.");
		}
	}
	
	
	

	
	
	
	
	
	
	
	
	public boolean hasRole(Role role) 
	{
		for (Role r : roles)
		{
			if (r.equals(role))
				return true;
		}
		
		return false;
	}
	
	public void grantRole(Role role)
	{
		if (!hasRole(role))
			roles.add(role);
	}
	
	public void removeRole(Role role)
	{
		if (hasRole(role))
			roles.remove(role);
	}
	
	
	
	
	
	
	
	
	
	public static boolean isValidUsername(String username)
	{
		return ApplicationConstants.usernameRegEx.matcher(username).matches();
	}
	
	public static boolean isValidPassword(StringBuffer password)
	{
		return ApplicationConstants.passwordRegEx.matcher(password).matches();
	}
	
	

}
