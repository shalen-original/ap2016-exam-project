package ap2016.entities;


import ap2016.application.ApplicationUtilities;
import ap2016.exceptions.InvalidURLException;


/**
 * This class represents a single news.
 * @author Matteo Nardini
 *
 */
public class News
{

	/**
	 * Contains the title of the news.
	 */
	private String title;
	/**
	 * Contains the link of the news.
	 */
	private String link;
	/**
	 * Contains the publication date of the news.
	 */
	/*
	 * This field is not a "Date". Using the "Date" type has proven to create serious issues when
	 * parsing from a new "import" file. Moreover, dates can be represented in many ways as a String
	 * and Date only offers deprecated methods to parse String.
	 */
	private String pubblicationDate;
	/**
	 * Contains the author of the news.
	 */
	private String author;
	/**
	 * Contains the description of the news.
	 */
	private String description;
	/**
	 * Contains the content of the news.
	 */
	private String content;

	public News()
	{
		this.title = "";
		this.link = "";
		this.pubblicationDate = "";
		this.author = "";
		this.description = "";
		this.content = "";
	}

	/**
	 * Allows to access the author of the news.
	 * @return Returns the author of the news.
	 */
	public String getAuthor()
	{
		return this.author;
	}

	/**
	 * Allows to access the content of the news.
	 * @return Returns the content of the news.
	 */
	public String getContent()
	{
		return this.content;
	}

	/**
	 * Allows to access the description of the news.
	 * @return Returns the description of the news.
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * Allows to access the link of the news.
	 * @return Returns the link of the news.
	 */
	public String getLink()
	{
		return this.link;
	}

	/**
	 * Allows to access the publication date of the news.
	 * @return Returns the publication date of the news.
	 */
	public String getPubblicationDate()
	{
		return this.pubblicationDate;
	}

	/**
	 * Allows to access the title of the news.
	 * @return Returns the title of the news.
	 */
	public String getTitle()
	{
		return this.title;
	}

	/**
	 * Allows to set the author of the news.
	 * @param author The new author of the news.
	 */
	public void setAuthor(String author)
	{
		this.author = author;
	}

	/**
	 * Allows to set the content of the news.
	 * @param content The new content of the news.
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * Allows to set the description of the news.
	 * @param description The new description of the news.
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Allows to set the link of the news. Internally, the new link is validated through the {@link ap2016.application.ApplicationUtilities#isValidURL(String) isValidURL} method.
	 * @param link The new link of the news.
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
	 * Allows to set the publication date of the news.
	 * @param pubblicationDate The new publication of the news.
	 */
	public void setPubblicationDate(String pubblicationDate)
	{
		this.pubblicationDate = pubblicationDate;
	}

	/**
	 * Allows to set the title of the news.
	 * @param title The new title of the news.
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

}
