package ap2016.entities;


import java.util.ArrayList;

import ap2016.application.ApplicationUtilities;
import ap2016.exceptions.InvalidLanguageStringException;
import ap2016.exceptions.InvalidURLException;


/**
 * This class represents a news channel. A news channel is a list of {@link ap2016.entities.News News} which have the same origin.
 * @author Matteo Nardini
 *
 */
public class NewsChannel
{

	/**
	 * Contains the list of news of this channel.
	 */
	private ArrayList<News> news;
	/**
	 * Contains the title of this channel.
	 */
	private String title;
	/**
	 * Contains the link to this channel.
	 */
	private String link;
	/**
	 * Contains the description of this channel.
	 */
	private String description;
	/**
	 * Contains the language string representing the language of this channel.
	 */
	private String language;

	/**
	 * Creates a new news channel which parameter are all empty Strings.
	 */
	public NewsChannel()
	{
		this("", "", "", "");
	}

	/**
	 * Creates a new news channel. The link of the new channel is validated through the {@link ap2016.application.ApplicationUtilities#isValidURL(String) isValidURL} method. The language of the new channel is validated through the {@link ap2016.application.ApplicationUtilities#isValidLanguage(String) isValidLanguage} method.
	 * @param title The title of the new news channel.
	 * @param link The link of the new news channel.
	 * @param description The description of the new news channel.
	 * @param language The language string used by the new news channel.
	 * @throws InvalidURLException If the new link is not a valid link.
	 * @throws InvalidLanguageStringException If the new language string is not valid.
	 */
	public NewsChannel(String title, String link, String description, String language)
	{
		setTitle(title);
		setLink(link);
		setDescription(description);
		setLanguage(language);

		this.news = new ArrayList<>();
	}

	/**
	 * Allows to access the description of this channel.
	 * @return Returns the description of this channel.
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * Allows to access the language of this channel.
	 * @return Returns the language of this channel.
	 */
	public String getLanguage()
	{
		return this.language;
	}

	/**
	 * Allows to access the link of this channel.
	 * @return Returns the link of this channel.
	 */
	public String getLink()
	{
		return this.link;
	}

	/**
	 * Allows to access the list of news of this channel.
	 * @return Returns the list of news of this channel.
	 */
	public ArrayList<News> getNews()
	{
		return this.news;
	}

	/**
	 * Allows to access the title of this channel.
	 * @return Returns the title of this channel.
	 */
	public String getTitle()
	{
		return this.title;
	}

	/**
	 * Allows to set the description of this channel.
	 * @param description The new description of this channel.
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Allows to set the language of this channel. Internally, the new language is validated through the {@link ap2016.application.ApplicationUtilities#isValidLanguage(String) isValidLanguage} method.
	 * @param language The new language string of this channel.
	 * @throws InvalidLanguageStringException If the new language is not a valid language string.
	 */
	public void setLanguage(String language)
	{
		if (ApplicationUtilities.isValidLanguage(language))
		{
			this.language = language;
		} else
		{
			throw new InvalidLanguageStringException("The language \"" + language + "\" is not valid.");
		}
	}

	/**
	 * Allows to set the link of this channel. Internally, the new link is validated through the {@link ap2016.application.ApplicationUtilities#isValidURL(String) isValidURL} method.
	 * @param link The new link of this channel.
	 * @throws InvalidURLException If the new link is not a valid link.
	 */
	public void setLink(String link)
	{
		if (ApplicationUtilities.isValidURL(link))
		{
			this.link = link;
		} else
		{
			throw new InvalidURLException("The url \"" + link + "\" is not valid.");
		}
	}

	/**
	 * Allows to set the title of this channel.
	 * @param title The new title of this channel.
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Simple alias for {@link #getTitle() getTitle}.
	 */
	@Override
	public String toString()
	{
		return getTitle();
	}

}
