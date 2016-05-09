package tests.ap2016.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import ap2016.entities.Role;
import ap2016.entities.User;

@SuppressWarnings("unused")
public class UserTests {

	@Test
	public void setUsernameTest_00()
	{
		User u = new User("test01", new StringBuffer("123456789"), "test", null);
		assertEquals("test01", u.getUsername());
	}
	
	@Test
	public void setUsernameTest_01()
	{
		User u = new User("test01", new StringBuffer("123456789"), "test", null);
		u.setUsername("test02");
		assertEquals("test02", u.getUsername());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setUsernameTest_02()
	{
		User u = new User("gb=sefds_", new StringBuffer("123456789"), "test", null);
	}
	
	
	
	
	
	
	
	
	@Test
	public void isValidUsername_00()
	{
		assertEquals(true, User.isValidUsername("test01"));
	}
	
	@Test
	public void isValidUsername_01()
	{
		assertEquals(true, User.isValidUsername("t-es_t'0.1"));
	}
	
	@Test
	public void isValidUsername_02()
	{
		assertEquals(false, User.isValidUsername("gb=sef,ds_"));
	}
	
	@Test
	public void isValidUsername_03()
	{
		assertEquals(false, User.isValidUsername("sdfgxc<>+"));
	}
	
	@Test
	public void isValidUsername_04()
	{
		assertEquals(false, User.isValidUsername("asdfcx.ffg.f"));
	}
	
	
	
	
	@Test
	public void setNewPasswordisRightPassword_00()
	{
		User u = new User("test01", new StringBuffer("123456789"), "test", null);
		u.setNewPassword(new StringBuffer("TestPassword"));
		
		assertEquals(true, u.isRightPassword(new StringBuffer("TestPassword")));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setNewPasswordisRightPassword_01()
	{
		User u = new User("test01", new StringBuffer("34"), "test", null);
	}
	
	
	@Test
	public void isValidPassword_00()
	{
		assertEquals(true, User.isValidPassword(new StringBuffer("12345678")));
	}
	
	@Test
	public void isValidPassword_01()
	{
		assertEquals(true, User.isValidPassword(new StringBuffer("àùàfgbhd46853-:.-")));
	}
	
	@Test
	public void isValidPassword_02()
	{
		assertEquals(false, User.isValidPassword(new StringBuffer("1234567")));
	}
	
	@Test
	public void isValidPassword_03()
	{
		assertEquals(false, User.isValidPassword(new StringBuffer("dg.à73")));
	}
		
	
	
	
	
	@Test
	public void hasRole_00()
	{
		User u = new User("test01", new StringBuffer("123456789"), "test", null);
		
		assertEquals(false, u.hasRole(Role.READ));
	}
	
	@Test
	public void hasRole_01()
	{
		User u = new User("test01", new StringBuffer("123456789"), "test", null);
		u.grantRole(Role.READ);
		assertEquals(true, u.hasRole(Role.READ));
	}
	
	@Test
	public void hasRole_02()
	{
		User u = new User("test01", new StringBuffer("123456789"), "test", null);
		u.grantRole(Role.READ);
		u.grantRole(Role.DELETE_NEWS);
		u.grantRole(Role.READ);
		assertEquals(true, u.hasRole(Role.READ));
	}
	
	@Test
	public void hasRole_03()
	{
		User u = new User("test01", new StringBuffer("123456789"), "test", null);
		u.grantRole(Role.READ);
		u.grantRole(Role.DELETE_NEWS);
		u.removeRole(Role.READ);
		assertEquals(false, u.hasRole(Role.READ));
	}
	
	@Test
	public void hasRole_04()
	{
		User u = new User("test01", new StringBuffer("123456789"), "test", null);
		u.removeRole(Role.READ);
		assertEquals(false, u.hasRole(Role.READ));
	}
	
	
	
}
