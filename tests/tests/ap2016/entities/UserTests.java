package tests.ap2016.entities;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import ap2016.application.ApplicationConstants;
import ap2016.entities.Role;
import ap2016.entities.User;


@SuppressWarnings("unused")
public class UserTests
{

	@Test
	public void setAvatarFile_00()
	{
		User u = new User("test01", "123456789".toCharArray());
		try
		{
			u.setAvatar(new File(ApplicationConstants.assetsBase + "\\test"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Test(expected = IOException.class)
	public void setAvatarFile_01() throws IOException
	{
		User u = new User("test01", "123456789".toCharArray());
		u.setAvatar(new File(ApplicationConstants.assetsBase + "\\garbagename"));
	}

	@Test
	public void setAvatarFromName()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.setAvatarFromName("test");
	}

	@Test
	public void setAvatarString()
	{
		User u = new User("test01", "123456789".toCharArray());
		try
		{
			u.setAvatar(ApplicationConstants.assetsBase + "\\test");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAvatar()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.getAvatar();
	}

	@Test
	public void testGetAvatarName()
	{
		User u = new User("test01", "123456789".toCharArray());
		assertEquals("default", u.getAvatarName());
	}

	@Test
	public void testGetCurrentPasswordHash()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.getCurrentPasswordHash();
	}

	@Test
	public void testGetCurrentPasswordSalt()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.getCurrentPasswordSalt();
	}

	@Test
	public void testGetRolesString_00()
	{
		User u = new User("test01", "123456789".toCharArray());
		assertEquals(u.getRolesString().size(), 0);
	}

	@Test
	public void testGetRolesString_01()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.grantRole(Role.READ);
		if (u.getRolesString().size() != 1)
		{
			fail("Arraylist of wrong size. Expected 1 but obtained " + u.getRolesString().size());
		}
		if (!u.getRolesString().get(0).equals("READ"))
		{
			fail("Wrong role returned. Expected READ but obtained " + u.getRolesString().get(0));
		}
	}

	@Test
	public void testGetRolesString_02()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.grantRole(Role.READ);
		u.grantRole(Role.SEARCH);
		if (u.getRolesString().size() != 2)
		{
			fail("Arraylist of wrong size. Expected 2 but obtained " + u.getRolesString().size());
		}
		if (!u.getRolesString().get(0).equals("READ"))
		{
			fail("Wrong role returned. Expected READ but obtained " + u.getRolesString().get(0));
		}
		if (!u.getRolesString().get(1).equals("SEARCH"))
		{
			fail("Wrong role returned. Expected SEARCH but obtained " + u.getRolesString().get(1));
		}
	}

	@Test
	public void testGetUsername()
	{
		User u = new User("test", "123456789".toCharArray());
		assertEquals("test", u.getUsername());
	}

	@Test
	public void testHasRole_00()
	{
		User u = new User("test01", "123456789".toCharArray());

		assertEquals(false, u.hasRole(Role.READ));
	}

	@Test
	public void testHasRole_01()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.grantRole(Role.READ);
		assertEquals(true, u.hasRole(Role.READ));
	}

	@Test
	public void testHasRole_02()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.grantRole(Role.READ);
		u.grantRole(Role.DELETE_NEWS);
		u.grantRole(Role.READ);
		assertEquals(true, u.hasRole(Role.READ));
	}

	@Test
	public void testHasRole_03()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.grantRole(Role.READ);
		u.grantRole(Role.DELETE_NEWS);
		u.removeRole(Role.READ);
		assertEquals(false, u.hasRole(Role.READ));
	}

	@Test
	public void testHasRole_04()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.removeRole(Role.READ);
		assertEquals(false, u.hasRole(Role.READ));
	}

	@Test
	public void testIsValidPassword_00()
	{
		assertEquals(true, User.isValidPassword("12345678".toCharArray()));
	}

	@Test
	public void testIsValidPassword_01()
	{
		assertEquals(true, User.isValidPassword("àùàfgbhd46853-:.-".toCharArray()));
	}

	@Test
	public void testIsValidPassword_02()
	{
		assertEquals(false, User.isValidPassword("1234567".toCharArray()));
	}

	@Test
	public void testIsValidPassword_03()
	{
		assertEquals(false, User.isValidPassword("dg.à73".toCharArray()));
	}

	@Test
	public void testIsValidPassword_04()
	{
		char[] a = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		assertEquals(true, User.isValidPassword(a));
	}

	@Test
	public void testIsValidUsername_00()
	{
		assertEquals(true, User.isValidUsername("test01"));
	}

	@Test
	public void testIsValidUsername_01()
	{
		assertEquals(true, User.isValidUsername("t-es_t'0.1"));
	}

	@Test
	public void testIsValidUsername_02()
	{
		assertEquals(false, User.isValidUsername("gb=sef,ds_"));
	}

	@Test
	public void testIsValidUsername_03()
	{
		assertEquals(false, User.isValidUsername("sdfgxc<>+"));
	}

	@Test
	public void testIsValidUsername_04()
	{
		assertEquals(false, User.isValidUsername("asdfcx.ffg.f"));
	}

	@Test
	public void testIsValidUsername_05()
	{
		assertEquals(false, User.isValidUsername(""));
	}

	@Test
	public void testSetNewPasswordisRightPassword_01()
	{
		User u = new User("test01", "123456789".toCharArray());

		assertEquals(true, u.isRightPassword("123456789".toCharArray()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNewPasswordisRightPassword_04()
	{
		User u = new User("test01", "34".toCharArray());
	}

	public void testSetNewPasswordisRightPassword_06()
	{
		char[] a = "MoreGarbage".toCharArray();
		User u = new User("test01", a);
		assertEquals(false, u.isRightPassword("MoreGarbage".toCharArray()));
	}

	@Test
	public void testSetUsername_00()
	{
		User u = new User("test01", "123456789".toCharArray());
		assertEquals("test01", u.getUsername());
	}

	@Test
	public void testSetUsername_01()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.setUsername("test02");
		assertEquals("test02", u.getUsername());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetUsername_02()
	{
		User u = new User("test01", "123456789".toCharArray());
		u.setUsername("gb=sefds_");
	}

	@Test
	public void testToString()
	{
		User u = new User("ThisIsValidTest", "123456789".toCharArray());
		assertEquals(u.toString(), "ThisIsValidTest");
	}

	@Test
	public void testUserStringByteByteStringRole()
	{
		User u = new User("test", new byte[1], new byte[1], "test", Role.READ, Role.SEARCH);
	}

	@Test
	public void testUserStringChar()
	{
		User u = new User("test", "MoreGarbage".toCharArray());
	}

	@Test
	public void testUserStringLByteLByteString()
	{
		User u = new User("test", new byte[1], new byte[1], "test");
	}

	@Test
	public void testUserStringStringBufferString()
	{
		User u = new User("test", "123456789".toCharArray());
	}

}
