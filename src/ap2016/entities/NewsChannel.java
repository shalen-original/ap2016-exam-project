package ap2016.entities;

import java.util.ArrayList;

import ap2016.application.ApplicationUtilities;

public class NewsChannel {

	private ArrayList<News> news;
	private String title;
	private String link;
	private String description;
	private String language;
	
	
	public NewsChannel(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
	}
	
	public NewsChannel(String title, String link, String description, String language) {
		this(title, link, description);
		this.language = language;
	}

	
	public ArrayList<News> getNews() {
		return news;
	}

	public void addNews(News news) {
		this.news.add(news);
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
			throw new IllegalArgumentException("The url \"" + link + "\" is not valid.");
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
		this.language = language;
	}
	
}
