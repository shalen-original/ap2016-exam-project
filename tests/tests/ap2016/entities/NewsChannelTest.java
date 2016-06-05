package tests.ap2016.entities;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ap2016.entities.News;
import ap2016.entities.NewsChannel;


public class NewsChannelTest
{

	NewsChannel n;
	ArrayList<News> original;

	@Before
	public void setup()
	{
		this.n = new NewsChannel("Test Title", "http://www.example.org", "Test Description", "ts-TS");

		News a;
		this.original = new ArrayList<>();

		for (int i = 0; i < 10; i++)
		{
			a = new News();
			a.setTitle("Test News Title " + (i + 1));
			this.n.getNews().add(a);
			this.original.add(a);
		}
	}

	@After
	public void teardown()
	{
		this.n = null;
	}

	@Test
	public void testGetDescription()
	{
		assertEquals(this.n.getDescription(), "Test Description");
	}

	@Test
	public void testGetLanguage()
	{
		assertEquals(this.n.getLanguage(), "ts-TS");
	}

	@Test
	public void testGetLink()
	{
		assertEquals(this.n.getLink(), "http://www.example.org");
	}

	@Test
	public void testGetNews()
	{
		ArrayList<News> a = this.n.getNews();
		for (int i = 0; i < this.original.size(); i++)
		{
			if (!this.original.get(i).equals(a.get(i)))
			{
				fail("The news are not the same");
			}
		}
	}

	@Test
	public void testGetTitle()
	{
		assertEquals(this.n.getTitle(), "Test Title");
	}

	@Test
	public void testNewsChannelStringStringStringString()
	{
		this.n = new NewsChannel("Test Title 2", "http://next.example.org", "Test Description 2", "ts-TV");

		if (!this.n.getTitle().equals("Test Title 2"))
		{
			fail("Title doesn't match. Obtained [" + this.n.getTitle() + "] but expected [Test Title 2]");
		}
		if (!this.n.getLink().equals("http://next.example.org"))
		{
			fail("Link doesn't match. Obtained [" + this.n.getLink() + "] but expected [http://next.example.org]");
		}
		if (!this.n.getDescription().equals("Test Description 2"))
		{
			fail("Title doesn't match. Obtained [" + this.n.getDescription() + "] but expected [Test Description 2]");
		}
		if (!this.n.getLanguage().equals("ts-TV"))
		{
			fail("Title doesn't match. Obtained [" + this.n.getLanguage() + "] but expected [ts-TV]");
		}
	}

	@Test
	public void testSetDescription()
	{
		this.n.setDescription("Test Description 2");
		assertEquals(this.n.getDescription(), "Test Description 2");
	}

	@Test
	public void testSetLanguage_00()
	{
		this.n.setLanguage("ts-TV");
		assertEquals(this.n.getLanguage(), "ts-TV");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetLanguage_01()
	{
		this.n.setLanguage("invalid language");
	}

	@Test
	public void testSetLink_00()
	{
		this.n.setLink("http://next.example.org");
		assertEquals(this.n.getLink(), "http://next.example.org");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetLink_01()
	{
		this.n.setLink("invalid url");
	}

	@Test
	public void testSetTitle()
	{
		this.n.setTitle("Test Title 2");
		assertEquals(this.n.getTitle(), "Test Title 2");
	}

	@Test
	public void testToString()
	{
		assertEquals(this.n.toString(), "Test Title");
	}

}
