package tests.ap2016.io;


import static org.junit.Assert.fail;

import java.util.ArrayList;

import javax.xml.transform.TransformerFactoryConfigurationError;

import org.junit.Test;

import ap2016.entities.NewsChannel;
import ap2016.io.NewsChannelDataProvider;


public class NewsChannelDataProviderTest
{

	@Test
	public void testGetData()
	{
		NewsChannelDataProvider.getInstance().getData();
	}

	@Test
	public void testGetInstance()
	{
		NewsChannelDataProvider.getInstance();
	}

	@Test
	public void testSaveReadDataToFile() throws TransformerFactoryConfigurationError, Exception
	{
		NewsChannelDataProvider.getInstance().readDataFromFile();
		ArrayList<NewsChannel> us = NewsChannelDataProvider.getInstance().getData();

		@SuppressWarnings("unchecked")
		ArrayList<NewsChannel> origin = (ArrayList<NewsChannel>) us.clone();

		NewsChannelDataProvider.getInstance().saveDataToFile();
		NewsChannelDataProvider.getInstance().readDataFromFile();

		for (int i = 0; i < origin.size(); i++)
		{
			if (!origin.get(i).getTitle().equals(us.get(i).getTitle()))
			{
				fail("data file was not read correctly");
			}

			if (!origin.get(i).getDescription().equals(us.get(i).getDescription()))
			{
				fail("data file was not read correctly");
			}

			if (!origin.get(i).getLink().equals(us.get(i).getLink()))
			{
				fail("data file was not read correctly");
			}

			if (!origin.get(i).getLanguage().equals(us.get(i).getLanguage()))
			{
				fail("data file was not read correctly");
			}

			for (int j = 0; j < origin.get(i).getNews().size(); j++)
			{
				if (!origin.get(i).getNews().get(j).getTitle().equals(us.get(i).getNews().get(j).getTitle()))
				{
					fail("data file was not read correctly");
				}
				if (!origin.get(i).getNews().get(j).getAuthor().equals(us.get(i).getNews().get(j).getAuthor()))
				{
					fail("data file was not read correctly");
				}
				if (!origin.get(i).getNews().get(j).getContent().equals(us.get(i).getNews().get(j).getContent()))
				{
					fail("data file was not read correctly");
				}
				if (!origin.get(i).getNews().get(j).getDescription()
						.equals(us.get(i).getNews().get(j).getDescription()))
				{
					fail("data file was not read correctly");
				}
				if (!origin.get(i).getNews().get(j).getLink().equals(us.get(i).getNews().get(j).getLink()))
				{
					fail("data file was not read correctly");
				}
				if (!origin.get(i).getNews().get(j).getPubblicationDate()
						.equals(us.get(i).getNews().get(j).getPubblicationDate()))
				{
					fail("data file was not read correctly");
				}
			}
		}
	}

}
