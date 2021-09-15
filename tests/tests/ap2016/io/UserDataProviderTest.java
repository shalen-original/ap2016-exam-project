package tests.ap2016.io;


import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.transform.TransformerFactoryConfigurationError;

import org.junit.Test;

import ap2016.entities.Role;
import ap2016.entities.User;
import ap2016.io.UserDataProvider;


public class UserDataProviderTest
{

	private void fillListWithTestData(ArrayList<User> us)
	{
		User u;

		u = new User("Username1", "Password1".toCharArray());
		u.grantRole(Role.READ);
		us.add(u);

		u = new User("Username2", "Password2".toCharArray());
		u.grantRole(Role.READ);
		u.grantRole(Role.SEARCH);
		us.add(u);

		u = new User("Username3", "Password3".toCharArray());
		us.add(u);
	}

	@Test
	public void testGetData()
	{
		UserDataProvider.getInstance().getData();
	}

	@Test
	public void testGetInstance()
	{
		UserDataProvider.getInstance();
	}

	@Test
	public void testSaveReadDataToFile() throws TransformerFactoryConfigurationError, Exception
	{
		ArrayList<User> us = UserDataProvider.getInstance().getData();
		fillListWithTestData(us);

		@SuppressWarnings("unchecked")
		ArrayList<User> origin = (ArrayList<User>) us.clone();

		UserDataProvider.getInstance().saveDataToFile();
		UserDataProvider.getInstance().readDataFromFile();

		for (int i = 0; i < origin.size(); i++)
		{
			if (!origin.get(i).getUsername().equals(us.get(i).getUsername()))
			{
				fail("users file was not read correctly");
			}

			if (!origin.get(i).getAvatarName().equals(us.get(i).getAvatarName()))
			{
				fail("users file was not read correctly");
			}

			if (!origin.get(i).getRolesString().equals(us.get(i).getRolesString()))
			{
				fail("users file was not read correctly");
			}

			if (!Arrays.equals(origin.get(i).getCurrentPasswordHash(), us.get(i).getCurrentPasswordHash()))
			{
				fail("users file was not read correctly");
			}

			if (!Arrays.equals(origin.get(i).getCurrentPasswordSalt(), us.get(i).getCurrentPasswordSalt()))
			{
				fail("users file was not read correctly");
			}
		}
	}

}
