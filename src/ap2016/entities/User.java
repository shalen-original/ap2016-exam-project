package ap2016.entities;


import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
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
import ap2016.exceptions.InvalidAvatarNameException;
import ap2016.exceptions.InvalidPasswordException;
import ap2016.exceptions.InvalidUsernameException;


/**
 * This class represents a user of the application.
 * @author Matteo Nardini
 *
 */
public class User
{

	/**
	 * The number of iteration used when hashing the user password.
	 */
	private final static int HASH_ITERATION_COUNT = 10000;

	/**
	 * Allows to easily generated a random salt that can be used to more securely hash a payload.
	 * @param bytesNum The number of bytes that should compose the salt.
	 * @return An array containing the randomly generated salt.
	 */
	private static byte[] generateRandomSalt(int bytesNum)
	{
		byte[] salt = null;

		try
		{
			salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(bytesNum);
		} catch (NoSuchAlgorithmException e)
		{
			// This should really never never happen. Never say never, but this time I'm pretty
			// sure.
			// Unless someone changes the string "SHA1PRNG". Don't do it.
		}

		return salt;
	}

	/**
	 * Allows to hash an image and to get back the string containing the hash.
	 * @param image The image to be hashed.
	 * @return The string containing the hash of the image.
	 */
	private static String hashImage(RenderedImage image)
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try
		{
			ImageIO.write(image, "png", outputStream);
		} catch (IOException e1)
		{
			// This cannot happen. I'm not writing on a file but on an internal byte array stream.
		}
		byte[] data = outputStream.toByteArray();

		byte[] hash = null;
		try
		{
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			md.update(data);
			hash = md.digest();
		} catch (NoSuchAlgorithmException e)
		{
			// This should really never never happen. Never say never, but this time I'm pretty
			// sure.
			// Unless someone changes the string "MD5". Don't do it.
		}

		if (hash == null)
		{
			return null;
		}

		StringBuilder ans = new StringBuilder();

		for (int i = 0; i < hash.length; i++)
		{
			ans.append(Integer.toHexString(hash[i]));
		}

		return ans.toString();

	}

	/**
	 * This method allows to easily hash a given char sequence using a given salt. It internally uses the algorithm PBKDF2 for hashing.
	 * @param toHash The char sequence to be hashed.
	 * @param salt The salt to be used for the hash.
	 * @return Returns the hashed version of the original {@code toHash} char sequence.
	 */
	private static byte[] hashPassword(char[] toHash, byte[] salt)
	{
		byte[] hash = null;

		PBEKeySpec spec = new PBEKeySpec(toHash, salt, HASH_ITERATION_COUNT, 64 * 8);

		try
		{
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = skf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e)
		{
			// This should really never never happen. Never say never, but this time I'm pretty
			// sure.
			// Unless someone changes the string "PBKDF2WithHmacSHA1". Don't do it.
		} catch (InvalidKeySpecException e)
		{
			// Also this should never happen.
		}

		return hash;
	}

	/**
	 * Allows to check the validity of a password.
	 * @param password The password to be validated.
	 * @return The method returns {@code true} if the given password is valid, {@code false} otherwise.
	 */
	public static boolean isValidPassword(char[] password)
	{
		StringBuffer a = new StringBuffer();
		a.append(password);
		return ApplicationConstants.passwordRegEx.matcher(a).matches();
	}

	/**
	 * Allows to check the validity of a username.
	 * @param username The username to be validated.
	 * @return The method returns {@code true} if the given username is valid, {@code false} otherwise.
	 */
	public static boolean isValidUsername(String username)
	{
		return ApplicationConstants.usernameRegEx.matcher(username).matches() && (!username.equals(""));
	}

	/**
	 * Contains the username of the user
	 */
	private String username;

	/**
	 * Contains the salted hash of the password of the user.
	 */
	private byte[] passwordHash;

	/**
	 * Contains the salt used to hash the current password of the user.
	 */
	private byte[] currentPasswordSalt;

	/**
	 * Contains the avatar of the user.
	 */
	private ImageIcon avatar;

	/**
	 * Contains the name of the avatar image when stored internally by the program.
	 */
	private String avatarName;

	/**
	 * Contains all the roles granted to this user.
	 */
	private ArrayList<Role> roles;

	/**
	 * Creates a new user. The username is internally validated through the {@link User#isValidUsername(String) isValidUsername} method.
	 * @param username The username of the new user.
	 * @param passwordHash The hash of the user password, structures as array of byte.
	 * @param passwordSalt The salt used to hash the user password, structures as array of byte.
	 * @param avatarName The name of the avatar to load. The name of the avatar is how internally the application saves the avatar.
	 * @throws InvalidUsernameException If the username is not valid.
	 * @throws InvalidAvatarNameException If the avatar "default" is inaccessible.
	 * nvalidAvatarNameException If the avatar name is not found.
	 */
	public User(String username, byte[] passwordHash, byte[] passwordSalt, String avatarName)
	{
		this(username, avatarName, null);

		this.passwordHash = passwordHash;
		this.currentPasswordSalt = passwordSalt;

	}

	/**
	 * Creates a new user. The username is internally validated through the {@link User#isValidUsername(String) isValidUsername} method.
	 * @param username The username of the new user.
	 * @param passwordHash The hash of the user password, structures as array of byte.
	 * @param passwordSalt The salt used to hash the user password, structures as array of byte.
	 * @param avatarName The name of the avatar to load. The name of the avatar is how internally the application saves the avatar.
	 * @param roles The roles of the current user.
	 * @throws InvalidUsernameException If the username is not valid.
	 * @throws InvalidAvatarNameException If the avatar "default" is inaccessible.
	 * nvalidAvatarNameException If the avatar name is not found.
	 */
	public User(String username, byte[] passwordHash, byte[] passwordSalt, String avatarName, Role... roles)
	{
		this(username, avatarName, roles);

		this.passwordHash = passwordHash;
		this.currentPasswordSalt = passwordSalt;

	}

	/**
	 * Creates a new user. The username is internally validated through the {@link #isValidUsername(String) isValidUsername} method. The password is internally validated through the {@link User#isValidPassword(char[]) isValidPassword} method.
	 * @param username The username of the new user.
	 * @param newPassword The password of the new user.
	 * @throws InvalidUsernameException If the username is not valid.
	 * @throws InvalidPasswordException If the password is not valid.
	 * @throws InvalidAvatarNameException If the avatar "default" is inaccessible.
	 */
	public User(String username, char[] newPassword)
	{
		this(username, "default", null);

		setNewPassword(newPassword);
	}

	/**
	 * Creates a new user. The username is internally validated through the {@link User#isValidUsername(String) isValidUsername} method.
	 * @param username The username of the new user.
	 * @param avatarName The name of the avatar to load. The name of the avatar is how internally the application saves the avatar.
	 * @param roles The roles of the current user.
	 * @throws InvalidUsernameException If the username is not valid.
	 * @throws InvalidAvatarNameException If the avatar name is not found.
	 */
	private User(String username, String avatarName, Role[] roles)
	{
		setUsername(username);
		setAvatarFromName(avatarName);

		this.roles = new ArrayList<Role>();

		if (roles != null)
		{
			for (Role r : roles)
			{
				grantRole(r);
			}
		}
	}

	/**
	 * Allows to access the avatar image of this user.
	 * @return The method returns the avatar image of this user.
	 */
	public ImageIcon getAvatar()
	{
		return this.avatar;
	}

	/**
	 * Allows to access the avatar name of this user.
	 * @return The method returns the avatar name of this user.
	 */
	public String getAvatarName()
	{
		return this.avatarName;
	}

	/**
	 * Allows to access the hash of the current password of the user.
	 * @return The method returns the hash of the current password of the user.
	 */
	public byte[] getCurrentPasswordHash()
	{
		return this.passwordHash;
	}

	/**
	 * Allows to access the salt used to hash the current password of the user.
	 * @return The method returns the salt used to hash the current password of the user.
	 */
	public byte[] getCurrentPasswordSalt()
	{
		return this.currentPasswordSalt;
	}

	/**
	 * Allows to obtain a string representation of all the roles owned by the current user.
	 * @return The method returns a list of string in which every element of the list contains the name of a role owned by this user.
	 */
	public ArrayList<String> getRolesString()
	{
		ArrayList<String> ans = new ArrayList<>();

		for (Role r : this.roles)
		{
			ans.add(r.toString());
		}

		return ans;
	}

	/**
	 * Allows to access the username of the user.
	 * @return The method returns the username of the user.
	 */
	public String getUsername()
	{
		return this.username;
	}

	/**
	 * Grants (assigns) a certain role to the current user. If the current user already has this role, nothing happens.
	 * @param role The role to be granted (assigned) to the current user.
	 */
	public void grantRole(Role role)
	{
		if (!hasRole(role))
		{
			this.roles.add(role);
		}
	}

	/**
	 * Allows to check if this user has a certain role.
	 * @param role The role to check
	 * @return The method returns {@code true} if the current user has the role {@code role}. It returns {@code false} otherwise.
	 */
	public boolean hasRole(Role role)
	{
		for (Role r : this.roles)
		{
			if (r.equals(role))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Allows to check is a certain password is the one of this user.
	 * @param pwd The password to be checked.
	 * @return The method returns {@code true} is the given password is the same as the one of this user. It returns {@code false} otherwise.
	 */
	public boolean isRightPassword(char[] pwd)
	{
		return Arrays.equals(this.passwordHash, hashPassword(pwd, this.currentPasswordSalt));
	}

	/**
	 * Allows to remove a certain role from the current user. If the user doesn't have the role {@code role}, nothing happens.
	 * @param role The role to be deleted.
	 */
	public void removeRole(Role role)
	{
		if (hasRole(role))
		{
			this.roles.remove(role);
		}
	}

	/**
	 * Allows to set the avatar of the user. The avatar associated through this method is copied in the program's asset folder and from now
	 * on will be referred to through its name.
	 * @param file The file containing the image that as to become the avatar.
	 * @throws IOException If the file is not valid or not reachable.
	 */
	public void setAvatar(File file) throws IOException
	{
		BufferedImage tmp = null;

		// Read the image
		tmp = ImageIO.read(file);

		// Generate hash for the image. Now this will be the name of the image.
		// By hashing we are granted to have a unique name for this image.
		this.avatarName = hashImage(tmp);

		// Writes to a file "internal" to the program
		try (BufferedOutputStream outputStream = new BufferedOutputStream(
				new FileOutputStream(Paths.get(ApplicationConstants.assetsBase, this.avatarName).toString())))
		{
			ImageIO.write(tmp, "png", outputStream);
		}

		// Reads from the internal file (after next GC, the original image will not be used by the
		// process anymore).
		tmp = ImageIO.read(Paths.get(ApplicationConstants.assetsBase, this.avatarName).toFile());

		// Sets the new avatar.
		this.avatar = new ImageIcon(tmp);

	}

	/**
	 * Allows to set the avatar of the user. The avatar associated through this method is copied in the program's asset folder and from now
	 * on will be referred to through its name.
	 * @param path The path of the file containing the image that as to become the avatar.
	 * @throws IOException If the path is not valid or the file is not reachable.
	 */
	public void setAvatar(String path) throws IOException
	{
		setAvatar(new File(path));
	}

	/**
	 * Allows to set the avatar of the user. This method assumes that the desired avatar has already been associated once through the {@link #setAvatar(File) setAvatar} method.
	 * @param avatarName The name of the avatar to load.
	 * @throws InvalidAvatarNameException If an avatar with the supplied avatar name has not been found.
	 */
	public void setAvatarFromName(String avatarName)
	{
		try
		{
			this.avatarName = avatarName;
			this.avatar = new ImageIcon(ImageIO.read(Paths.get(ApplicationConstants.assetsBase, avatarName).toFile()));
		} catch (IOException e)
		{
			throw new InvalidAvatarNameException("The avatar with the supplied avatar name was not found");
		}

	}

	/**
	 * Allows to set the password of the user. The new password is internally validated through the {@link #isValidPassword(char[]) isValidPassword} method.
	 * @param newPassword The new password.
	 * @throws InvalidPasswordException If the new password is not valid.
	 */
	public void setNewPassword(char[] newPassword)
	{
		if (isValidPassword(newPassword))
		{
			// The algorithm generates a random salt that will be used to hash this password
			this.currentPasswordSalt = generateRandomSalt(16);
			// Then hashes the given password with the generated salt.
			this.passwordHash = hashPassword(newPassword, this.currentPasswordSalt);
		} else
		{
			throw new InvalidPasswordException("The password is not valid.");
		}
	}

	/**
	 * Allows to set the username of the user. The new username is internally validated through the {@link User#isValidUsername(String) isValidUsername} method.
	 * @param username The new username.
	 * @throws InvalidUsernameException If the new username is not valid.
	 */
	public void setUsername(String username)
	{
		if (isValidUsername(username))
		{
			this.username = username;
		} else
		{
			throw new InvalidUsernameException("The username \"" + username + "\" is not valid.");
		}
	}

	/**
	 * Basically an alias of the {@link #getUsername() getUsername} method.
	 */
	@Override
	public String toString()
	{
		return getUsername();
	}

}
