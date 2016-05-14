package ap2016.entities;

import ap2016.application.ApplicationUtilities;

public class News {

	private String title;
	private String link;
	private String pubblicationDate;
	private String author;
	private String description;
	private String content;
	
	
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
	public String getPubblicationDate() {
		return pubblicationDate;
	}
	public void setPubblicationDate(String pubblicationDate) {
		this.pubblicationDate = pubblicationDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
