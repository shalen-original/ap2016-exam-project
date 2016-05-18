package ap2016.entities;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ap2016.application.ApplicationConstants;

public class User {
	
	private String username;
	private byte[] passwordHash;
	private byte[] currentPasswordSalt;
	
	private ImageIcon avatar;
	private String avatarName;
	
	private ArrayList<Role> roles;

	
	private final static int HASH_ITERATION_COUNT = 10000;		
	
	
	private User(String username, String avatarName, Role[] roles)
	{
		setUsername(username);
		setAvatarFromName(avatarName);
		
		this.roles = new ArrayList<Role>();
		
		if (roles != null)
		{
			for (Role r : roles)
				this.roles.add(r);
		}
	}
	
	
	public User(String username, byte[] passwordHash, byte[] passwordSalt, String avatarName)
	{
		this(username, avatarName, null);
		
		this.passwordHash = passwordHash;
		this.currentPasswordSalt = passwordSalt;
		
	}
	
	public User(String username, StringBuffer newPassword, String avatarName)
	{
		this(username, avatarName, null);
		
		setNewPassword(newPassword);
		
	}
	
	public User(String username, char[] newPassword)
	{
		this(username, "default", null);
		
		setNewPassword(newPassword);
		
	}
	
	public User(String username, byte[] passwordHash, byte[] passwordSalt, String avatarName, Role... roles)
	{
		this(username, avatarName, roles);
		
		this.passwordHash = passwordHash;
		this.currentPasswordSalt = passwordSalt;
		
	}
	
	public User(String username, StringBuffer newPassword, String avatarName, Role... roles)
	{
		this(username, avatarName, roles);
		
		setNewPassword(newPassword);
		
	}
	
	
	
	
	
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
	
	
	
	public void setNewPassword(char[] newPassword)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(newPassword);
		setNewPassword(sb);
	}
	
	public void setNewPassword(StringBuffer newPassword)
	{
		if (isValidPassword(newPassword))
		{
			currentPasswordSalt = generateRandomSalt(16);
			passwordHash = hashPassword(newPassword, currentPasswordSalt);
		}else{
			throw new IllegalArgumentException("The password is not valid.");
		}
	}

	public boolean isRightPassword(StringBuffer pwd)
	{
		return Arrays.equals(passwordHash, hashPassword(pwd, currentPasswordSalt));
	}
	
	public byte[] getCurrentPasswordSalt()
	{
		return currentPasswordSalt;
	}
	
	public byte[] getCurrentPasswordHash()
	{
		return passwordHash;
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
	
	public ArrayList<String> getRolesString()
	{
		ArrayList<String> ans = new ArrayList<>();
		
		for (Role r : roles)
		{
			ans.add(r.toString());
		}
		
		return ans;
	}
	
	
	
	
	
	
	public ImageIcon getAvatar()
	{
		return avatar;
	}
	
	public String getAvatarName()
	{
		return avatarName;
	}
	
	public void setAvatar(String path) throws IOException
	{
		setAvatar(new File(path));
	}
	
	public void setAvatar(File file) throws IOException
	{
		BufferedImage tmp = null;
		
		// Read the image
		tmp = ImageIO.read(file);
		
		// Generate (unique ?) hash
		avatarName = hashImage(tmp);
		
		// Writes to a file internal to the program
		try(BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(ApplicationConstants.assetsBase + avatarName)))
		{
			ImageIO.write(tmp, "png", outputStream);
		}
		
		// Reads from the internal file (after next GC, the original image will not be used by the process anymore).
		tmp = ImageIO.read(new File(ApplicationConstants.assetsBase + avatarName));
		
		// Sets the new avatar.
		avatar = new ImageIcon(tmp);
		
	}
	
	public void setAvatarFromName(String avatarName)
	{
		try {
			this.avatarName = avatarName;
			avatar = new ImageIcon(ImageIO.read(new File(ApplicationConstants.assetsBase + avatarName)));
		} catch (IOException e) {
			throw new IllegalArgumentException("The supplied avatar name was not found");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	public static boolean isValidUsername(String username)
	{
		return ApplicationConstants.usernameRegEx.matcher(username).matches() && (!username.equals(""));
	}
	
	public static boolean isValidPassword(char[] password)
	{
		StringBuffer a = new StringBuffer();
		a.append(password);
		return isValidPassword(a);
	}
	
	public static boolean isValidPassword(StringBuffer password)
	{
		return ApplicationConstants.passwordRegEx.matcher(password).matches();
	}
	
	
	
	
	
	
	
	private static byte[] hashPassword(StringBuffer toHash, byte[] salt)
	{
		  char[] charsToHash = new char[toHash.length()];
		  byte[] hash = null;
		  toHash.getChars(0, toHash.length() - 1, charsToHash, 0);
		  
		  PBEKeySpec spec = new PBEKeySpec(charsToHash, salt, HASH_ITERATION_COUNT, 64 * 8);
		  
		  try
		  {
			  SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			  hash = skf.generateSecret(spec).getEncoded();
		  }catch(NoSuchAlgorithmException e){
			// This should really never never happen. Never say never, but this time I'm pretty sure.
			// Unless someone changes the string "PBKDF2WithHmacSHA1". Don't do it.
		  }catch(InvalidKeySpecException e){
			  // TODO
		  }
		  
		  return hash;
	}

	private static byte[] generateRandomSalt(int bytesNum)
	{
	        byte[] salt = null;
	        
			try {
				salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(bytesNum);
			} catch (NoSuchAlgorithmException e) {
				// This should really never never happen. Never say never, but this time I'm pretty sure.
				// Unless someone changes the string "SHA1PRNG". Don't do it.
			}
			
	        return salt;
	}

	private static String hashImage(RenderedImage image) throws IOException
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        byte[] data = outputStream.toByteArray();
        
        byte[] hash = null;
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
	        md.update(data);
	        hash = md.digest();
		} catch (NoSuchAlgorithmException e) {
			// This should really never never happen. Never say never, but this time I'm pretty sure.
			// Unless someone changes the string "MD5". Don't do it.
		}
        
		if (hash == null)
			return null;
        
        StringBuilder ans = new StringBuilder();
        
        for (int i = 0; i < hash.length; i++)
        {
        	ans.append(Integer.toHexString(hash[i]));
        }
        
        return ans.toString();
        
	}
	
	@Override
	public String toString()
	{
		return getUsername();
	}
	
}
