package ap2016.entities;

import java.util.ArrayList;

import ap2016.application.ApplicationUtilities;
import ap2016.exceptions.InvalidLanguageStringException;
import ap2016.exceptions.InvalidURLException;

public class NewsChannel {

	private ArrayList<News> news;
	private String title;
	private String link;
	private String description;
	private String language;
	
	public NewsChannel()
	{
		this("", "", "", "");
	}
	
	public NewsChannel(String title, String link, String description, String language) {
		setTitle(title);
		setLink(link);
		setDescription(description);
		setLanguage(language);
		
		news = new ArrayList<>();
	}

	public ArrayList<News> getNews() {
		return news;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		if (ApplicationUtilities.isValidURL(link))
		{
			this.link = link;
		}else{
			throw new InvalidURLException("The url \"" + link + "\" is not valid.");
		}
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		if (ApplicationUtilities.isValidLanguage(language))
		{
			this.language = language;
		}else{
			throw new InvalidLanguageStringException("The language \"" + language + "\" is not valid.");
		}
	}
	
	@Override
	public String toString()
	{
		return this.title;
	}
	
}
