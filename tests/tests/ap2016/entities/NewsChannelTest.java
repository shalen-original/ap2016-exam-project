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
		n = new NewsChannel();
		n.setTitle("Test Title");
		n.setLink("http://www.example.org");
		n.setDescription("Test Description");
		n.setLanguage("ts-TS");
		
		News a;
		original = new ArrayList<>();
		
		for (int i = 0; i < 10; i++)
		{
			a = new News();
			a.setTitle("Test News Title " + (i + 1));
			n.addNews(a);
			original.add(a);
		}
	}
	
	@After
	public void teardown()
	{
		n = null;
	}
	
	
	@Test
	public void testNewsChannelStringStringString()
	{
		n = new NewsChannel("Test Title 2", "http://next.example.org", "Test Description 2");
		
		if (!n.getTitle().equals("Test Title 2"))
			fail("Title doesn't match. Obtained [" + n.getTitle() + "] but expected [Test Title 2]");
		if (!n.getLink().equals("http://next.example.org"))
			fail("Link doesn't match. Obtained [" + n.getLink() + "] but expected [http://next.example.org]");
		if (!n.getDescription().equals("Test Description 2"))
			fail("Title doesn't match. Obtained [" + n.getDescription() + "] but expected [Test Description 2]");
	}

	@Test
	public void testNewsChannelStringStringStringString()
	{
		n = new NewsChannel("Test Title 2", "http://next.example.org", "Test Description 2", "ts-TV");
		
		if (!n.getTitle().equals("Test Title 2"))
			fail("Title doesn't match. Obtained [" + n.getTitle() + "] but expected [Test Title 2]");
		if (!n.getLink().equals("http://next.example.org"))
			fail("Link doesn't match. Obtained [" + n.getLink() + "] but expected [http://next.example.org]");
		if (!n.getDescription().equals("Test Description 2"))
			fail("Title doesn't match. Obtained [" + n.getDescription() + "] but expected [Test Description 2]");
		if (!n.getLanguage().equals("ts-TV"))
			fail("Title doesn't match. Obtained [" + n.getLanguage() + "] but expected [ts-TV]");
	}

	@Test
	public void testGetNews()
	{
		ArrayList<News> a = n.getNews();
		for (int i = 0; i < original.size(); i++)
		{
			if (!original.get(i).equals(a.get(i)))
				fail("The news are not the same");
		}
	}

	@Test
	public void testAddNews()
	{
		News b = new News();
		b.setTitle("Test News Title Aut");
		n.addNews(b);
		original.add(b);

		ArrayList<News> a = n.getNews();
		for (int i = 0; i < original.size(); i++)
		{
			if (!original.get(i).equals(a.get(i)))
				fail("The news are not the same");
		}
		
	}
	
	@Test
	public void testRemoveNews()
	{
		News b = new News();
		b.setTitle("Test News Title Aut");
		n.addNews(b);
		original.add(b);

		n.removeNews(b);
		original.remove(b);
		
		ArrayList<News> a = n.getNews();
		for (int i = 0; i < original.size(); i++)
		{
			if (!original.get(i).equals(a.get(i)))
				fail("The news are not the same");
		}
	}

	@Test
	public void testGetTitle()
	{
		assertEquals(n.getTitle(), "Test Title");
	}

	@Test
	public void testSetTitle()
	{
		n.setTitle("Test Title 2");
		assertEquals(n.getTitle(), "Test Title 2");
	}

	@Test
	public void testGetLink()
	{
		assertEquals(n.getLink(), "http://www.example.org");
	}

	@Test
	public void testSetLink_00()
	{
		n.setLink("http://next.example.org");
		assertEquals(n.getLink(), "http://next.example.org");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testSetLink_01()
	{
		n.setLink("invalid url");
	}

	@Test
	public void testGetDescription()
	{
		assertEquals(n.getDescription(), "Test Description");
	}

	@Test
	public void testSetDescription()
	{
		n.setDescription("Test Description 2");
		assertEquals(n.getDescription(), "Test Description 2");
	}

	@Test
	public void testGetLanguage()
	{
		assertEquals(n.getLanguage(), "ts-TS");
	}

	@Test
	public void testSetLanguage_00()
	{
		n.setLanguage("ts-TV");
		assertEquals(n.getLanguage(), "ts-TV");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetLanguage_01()
	{
		n.setLanguage("invalid language");
	}
	
	@Test
	public void testToString()
	{
		assertEquals(n.toString(), "Test Title");
	}

}
