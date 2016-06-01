package ap2016.io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ap2016.entities.News;
import ap2016.entities.NewsChannel;

public class NewsChannelDataProvider extends DataProvider<NewsChannel>{
	
	
	private static NewsChannelDataProvider instance;
	public static NewsChannelDataProvider getInstance()
	{
		if (instance == null)
		{
			instance = new NewsChannelDataProvider();
		}
		
		return instance;
	}
	
	
	private NewsChannelDataProvider()
	{
		super("data.xml");
	}

	
	protected void parseDoc(Document doc)
	{
		NodeList channelNodes = doc.getElementsByTagName("channel");
		NodeList currentChannelChild;
		Node tmp;
		NewsChannel currentChannel;
		
		// Generally, a well formed RSS has only a single channel. This time, however, I will allow the user
		// to import its data into this file and will allow him/her to choose wether to add them to a new channel or
		// to the current one. Therefore, this file could contain more than a single channel. Thus, the for loop is required.
		
		for (int i = 0; i < channelNodes.getLength(); i++)
		{
			// Gets this channel data
			currentChannelChild = channelNodes.item(i).getChildNodes();
			
			// Creates the new channel
			currentChannel = new NewsChannel();		
			
			for (int j = 0; j < currentChannelChild.getLength(); j++)
			{
				tmp = currentChannelChild.item(j);
				switch(tmp.getNodeName())
				{
					case "title":
						currentChannel.setTitle(tmp.getTextContent());
						break;
					case "description":
						currentChannel.setDescription(tmp.getTextContent());
						break;
					case "link":
						currentChannel.setLink(tmp.getTextContent());
						break;
					case "language":
						currentChannel.setLanguage(tmp.getTextContent());
						break;
					case "item":
						currentChannel.getNews().add(buildNewFromNodeList(tmp));
						break;
				}
			}
			
			data.add(currentChannel);	
		}
	}
	
	private News buildNewFromNodeList(Node item)
	{
		News nn = new News();
		
		NodeList newsNode = item.getChildNodes();
		Node tmp;
		
		for (int i = 0; i < newsNode.getLength(); i++)
		{
			tmp = newsNode.item(i);
			
			switch(tmp.getNodeName())
			{
				case "title":
					nn.setTitle(tmp.getTextContent());
					break;
				case "link":
					nn.setLink(tmp.getTextContent());
					break;
				case "pubDate":
					nn.setPubblicationDate(tmp.getTextContent());
					break;
				case "dc:creator":
				case "author":
					nn.setAuthor(tmp.getTextContent());
				case "description":
					nn.setDescription(tmp.getTextContent());
					break;
				case "content:encoded":
				case "content":
					nn.setContent(tmp.getTextContent());
					break;
			}
		}
		
		return nn;
	}
	
	
	
	
	protected void buildDoc(Document doc)
	{
		// Creates the root elements and appends it to the document
		Element uRoot = doc.createElement("news-list");
		doc.appendChild(uRoot);
		
		Element currChannelNode = null;
		for (NewsChannel currentChannel : data)
		{
			// Creates this user
			currChannelNode = doc.createElement("channel");
			
			addElementWithText(currChannelNode, "title", currentChannel.getTitle(), doc);
			addElementWithText(currChannelNode, "description", currentChannel.getDescription(), doc);
			addElementWithText(currChannelNode, "link", currentChannel.getLink(), doc);
			addElementWithText(currChannelNode, "language", currentChannel.getLanguage(), doc);

			for (News n : currentChannel.getNews())
			{
				currChannelNode.appendChild(generateNodeFromNews("item", n, doc));
			}
			
			uRoot.appendChild(currChannelNode);
			
		}
	}
	
	private Element generateNodeFromNews(String elName, News n, Document doc)
	{
		Element el = doc.createElement(elName);
		
		addElementWithText(el, "title", n.getTitle(), doc);
		addElementWithText(el, "link", n.getLink(), doc);
		addElementWithText(el, "pubDate", n.getPubblicationDate(), doc);
		addElementWithText(el, "author", n.getAuthor(), doc);
		addElementWithText(el, "description", n.getDescription(), doc);
		addElementWithText(el, "content", n.getContent(), doc);
		
		return el;
	}


	@Override
	protected void appendDoc(Document doc)
	{
		parseDoc(doc);
	}

}
