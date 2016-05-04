package ap2016.entities;

import java.awt.Image;
import java.util.ArrayList;

import ap2016.application.ApplicationConstants;

public class User {
	
	private String username;
	private byte[] passwordHash;
	private byte[] currentPasswordHash;
	
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
	
	
	

	public boolean isRightPassword(StringBuffer pwd)
	{
		byte[] newPwdHash = hashStringBuffer(pwd);
		
		if (passwordHash.length != newPwdHash.length) return false;
		
		for (int i = 0; i <passwordHash.length; i++)
		{
			if(passwordHash[i] != newPwdHash[i])
				return false;
		}
		
		return true;
	}
	
	public void setNewPassword(StringBuffer newPassword)
	{
		if (isValidPassword(newPassword))
		{
			passwordHash = hashStringBuffer(newPassword);
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
	
	private static byte[] hashStringBuffer(StringBuffer toHash)
	{
		return null;
	}
	
//	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException 
//    {
//        String  originalPassword = "password";
//        String generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);
//        System.out.println(generatedSecuredPasswordHash);
//    }
//    private static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
//    {
//        int iterations = 1000;
//        char[] chars = password.toCharArray();
//        byte[] salt = getSalt();
//         
//        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
//        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        byte[] hash = skf.generateSecret(spec).getEncoded();
//        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
//    }
//     
//    private static byte[] getSalt() throws NoSuchAlgorithmException
//    {
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//        byte[] salt = new byte[16];
//        sr.nextBytes(salt);
//        return salt;
//    }
//     
//    private static String toHex(byte[] array) throws NoSuchAlgorithmException
//    {
//        BigInteger bi = new BigInteger(1, array);
//        String hex = bi.toString(16);
//        int paddingLength = (array.length * 2) - hex.length();
//        if(paddingLength > 0)
//        {
//            return String.format("%0"  +paddingLength + "d", 0) + hex;
//        }else{
//            return hex;
//        }
//    }
//     

}
