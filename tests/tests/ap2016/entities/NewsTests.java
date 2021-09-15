package tests.ap2016.entities;


import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
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
		this.n = new News();
		this.n.setTitle("Test Title");
		this.n.setLink("http://www.example.org");
		this.n.setPubblicationDate(DateFormat.getInstance().format(new Date(0)));
		this.n.setAuthor("Test Author");
		this.n.setDescription("Test Description");
		this.n.setContent("Test Content");
	}

	@After
	public void teardown()
	{
		this.n = null;
	}

	@Test
	public void testGetAuthor()
	{
		assertEquals(this.n.getAuthor(), "Test Author");
	}

	@Test
	public void testGetContent()
	{
		assertEquals(this.n.getContent(), "Test Content");
	}

	@Test
	public void testGetDescription()
	{
		assertEquals(this.n.getDescription(), "Test Description");
	}

	@Test
	public void testGetLink()
	{
		assertEquals(this.n.getLink(), "http://www.example.org");
	}

	@Test
	public void testGetPubblicationDate()
	{
		assertEquals(this.n.getPubblicationDate().toString(), "01/01/70 1.00");
	}

	@Test
	public void testGetTitle()
	{
		assertEquals(this.n.getTitle(), "Test Title");
	}

	@Test
	public void testSetAuthor()
	{
		this.n.setAuthor("Test Author 2");
		assertEquals(this.n.getAuthor(), "Test Author 2");
	}

	@Test
	public void testSetContent()
	{
		this.n.setContent("Test Content 2");
		assertEquals(this.n.getContent(), "Test Content 2");
	}

	@Test
	public void testSetDescription()
	{
		this.n.setDescription("Test Description 2");
		assertEquals(this.n.getDescription(), "Test Description 2");
	}

	@Test
	public void testSetLink_00()
	{
		this.n.setLink("http://www.next.example.org");
		assertEquals(this.n.getLink(), "http://www.next.example.org");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetLink_01()
	{
		this.n.setLink("invalid url");
	}

	@Test
	public void testSetPubblicationDate()
	{
		this.n.setPubblicationDate(DateFormat.getInstance().format(new Date(1000000)));
		assertEquals(this.n.getPubblicationDate().toString(), "01/01/70 1.16");
	}

	@Test
	public void testSetTitle()
	{
		this.n.setTitle("Test Title 2");
		assertEquals(this.n.getTitle(), "Test Title 2");
	}

}
