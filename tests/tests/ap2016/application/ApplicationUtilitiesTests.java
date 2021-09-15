package tests.ap2016.application;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JTextField;

import org.junit.Test;

import ap2016.application.ApplicationUtilities;


public class ApplicationUtilitiesTests
{

	@Test
	public void testByteArrayToString_00()
	{
		byte[] test = { 45, 36, 10, 47, 67, -10 };
		String a = Arrays.toString(test);

		assertEquals(a, ApplicationUtilities.byteArrayToString(test));
	}

	@Test
	public void testByteArrayToString_01()
	{
		byte[] test = {};
		String a = Arrays.toString(test);

		assertEquals(a, ApplicationUtilities.byteArrayToString(test));

	}

	@Test
	public void testFormatComponent_00()
	{
		JComponent a = new JTextField();
		ApplicationUtilities.formatComponent(a, true);
		ApplicationUtilities.formatComponent(a, false);
	}

	@Test
	public void testIsValidLanguage()
	{
		HashMap<String, Boolean> tests = new HashMap<>();

		tests.put("en-EN", true);
		tests.put("en-Ec", false);
		tests.put("En-EN", false);
		tests.put("en-ENF", false);
		tests.put("enf-EN", false);
		tests.put("en-sEf", false);
		tests.put("enD-EN", false);
		tests.put("ENenD", false);

		for (Map.Entry<String, Boolean> k : tests.entrySet())
		{
			if (ApplicationUtilities.isValidLanguage(k.getKey()) != k.getValue())
			{
				fail("The language: " + k.getKey() + " is marked as "
						+ (ApplicationUtilities.isValidLanguage(k.getKey()) ? "valid" : "invalid")
						+ " but should have been " + (k.getValue() ? "valid" : "invalid"));
			}
		}
	}

	@Test
	public void testIsValidURL()
	{
		HashMap<String, Boolean> tests = new HashMap<>();

		// The following list of valid/invalid urls has been kindly provided
		// by the website: https://mathiasbynens.be/demo/url-regex

		// VALID URLS
		tests.put("http://foo.com/blah_blah", true);
		tests.put("http://foo.com/blah_blah/", true);
		tests.put("http://www.example.com/wpstyle/?p=364", true);
		tests.put("https://www.example.com/foo/?bar=baz&inga=42&quux", true);
		tests.put("http://userid:password@example.com:8080", true);
		tests.put("http://userid:password@example.com:8080/", true);
		tests.put("http://userid@example.com", true);
		tests.put("http://userid@example.com/", true);
		tests.put("http://userid@example.com:8080", true);
		tests.put("http://userid@example.com:8080/", true);
		tests.put("http://userid:password@example.com", true);
		tests.put("http://userid:password@example.com/", true);
		tests.put("http://142.42.1.1/", true);
		tests.put("http://142.42.1.1:8080/", true);
		tests.put("http://code.google.com/events/#&product=browser", true);
		tests.put("http://j.mp", true);
		tests.put("ftp://foo.bar/baz", true);
		tests.put("http://foo.bar/?q=Test%20URL-encoded%20stuff", true);
		tests.put("http://1337.net", true);
		tests.put("http://a.b-c.de", true);
		tests.put("http://223.255.255.254", true);

		// INVALID URLS
		tests.put("ttp://", false);
		tests.put("://.", false);
		tests.put("://..", false);
		tests.put("://../", false);
		tests.put("://?", false);
		tests.put("://??", false);
		tests.put("://??/", false);
		tests.put("://#", false);
		tests.put("://##", false);
		tests.put("://##/", false);
		tests.put("://foo.bar?q=Spaces should be encoded", false);
		tests.put("//", false);
		tests.put("//a", false);
		tests.put("///a", false);
		tests.put("///", false);
		tests.put(":///a", false);
		tests.put("foo.com", false);
		tests.put("rdar://1234", false);
		tests.put("h://test", false);
		tests.put(":// shouldfail.com", false);
		tests.put(":// should fail", false);
		tests.put("://foo.bar/foo(bar)baz quux", false);
		tests.put("ftps://foo.bar/", false);
		tests.put("://-error-.invalid/", false);
		tests.put("://a.b--c.de/", false);
		tests.put("://-a.b.co", false);
		tests.put("://a.b-.co", false);
		tests.put("://0.0.0.0", false);
		tests.put("://10.1.1.0", false);
		tests.put("://10.1.1.255", false);
		tests.put("://224.1.1.1", false);
		tests.put("://1.1.1.1.1", false);
		tests.put("://123.123.123", false);
		tests.put("://3628126748", false);
		tests.put("://.www.foo.bar/", false);
		tests.put("://www.foo.bar./", false);
		tests.put("://.www.foo.bar./", false);
		tests.put("://10.1.1.1", false);

		for (Map.Entry<String, Boolean> k : tests.entrySet())
		{
			if (ApplicationUtilities.isValidURL(k.getKey()) != k.getValue())
			{
				fail("The url: " + k.getKey() + " is marked as "
						+ (ApplicationUtilities.isValidURL(k.getKey()) ? "valid" : "invalid") + " but should have been "
						+ (k.getValue() ? "valid" : "invalid"));
			}
		}
	}

	@Test
	public void testStringToByteArray_00()
	{
		byte[] test = { 45, 36, 10, 47, 67, -10 };
		String a = Arrays.toString(test);

		byte[] ans = ApplicationUtilities.stringToByteArray(a);

		for (int i = 0; i < test.length; i++)
		{
			if (test[i] != ans[i])
			{
				fail("The string has not been parsed correctly. The value " + ans[i] + "should have been " + test[i]);
			}
		}

	}

	@Test
	public void testStringToByteArray_01()
	{
		byte[] test = {};
		String a = Arrays.toString(test);

		byte[] ans = ApplicationUtilities.stringToByteArray(a);

		if (ans.length != 0)
		{
			fail("The length of the array should be zero");
		}

	}

}
