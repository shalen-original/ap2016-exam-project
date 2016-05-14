package tests.ap2016.entities;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ap2016.entities.News;

public class NewsTests
{

	private News n;
	
	@Before
	public void setup()
	{
		n = new News();
		n.setTitle("Test Title");
		n.setLink("http://www.example.org");
		n.setPubblicationDate(new Date(0));
		n.setAuthor("Test Author");
		n.setDescription("Test Description");
		n.setContent("Test Content");
	}
	
	@After
	public void teardown()
	{
		n = null;
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
		n.setLink("http://www.next.example.org");
		assertEquals(n.getLink(), "http://www.next.example.org");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testSetLink_01()
	{
		n.setLink("invalid url");
	}

	@Test
	public void testGetPubblicationDate()
	{
		assertEquals(n.getPubblicationDate().toString(), "Thu Jan 01 01:00:00 CET 1970");
	}

	@Test
	public void testSetPubblicationDate()
	{
		n.setPubblicationDate(new Date(1000));
		assertEquals(n.getPubblicationDate().toString(), "Thu Jan 01 01:00:01 CET 1970");
	}

	@Test
	public void testGetAuthor()
	{
		assertEquals(n.getAuthor(), "Test Author");
	}

	@Test
	public void testSetAuthor()
	{
		n.setAuthor("Test Author 2");
		assertEquals(n.getAuthor(), "Test Author 2");
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
	public void testGetContent()
	{
		assertEquals(n.getContent(), "Test Content");
	}

	@Test
	public void testSetContent()
	{
		n.setContent("Test Content 2");
		assertEquals(n.getContent(), "Test Content 2");
	}

}
