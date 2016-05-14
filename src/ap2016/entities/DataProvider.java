package ap2016.entities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import ap2016.application.ApplicationConstants;

public abstract class DataProvider <T>
{
	private ArrayList<T> data;
	private String filename;
	
	private DataProvider(String filename)
	{
		data = new ArrayList<T>();
		this.filename = filename;
	}
	
	public ArrayList<T> getData()
	{
		return data;
	}
	
	public void readDataFromFile() throws Exception
	{
		data.clear();
		try {
			
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(ApplicationConstants.dataBase + "\\" + filename));
			parseDoc(doc);
			
		} catch (IOException | ParserConfigurationException e) {
			throw new Exception("The username file located at \"" + ApplicationConstants.dataBase + "\\" + filename + "\" is not valid");
		} catch (SAXException e) {}	
	}
	
	
	public void saveDataToFile() throws TransformerFactoryConfigurationError, TransformerException
	{
		Document doc = null;
		try
		{
			// Starts building the new document
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			buildDoc(doc);
			
		} catch (ParserConfigurationException e) {}
		

		Transformer t = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(ApplicationConstants.dataBase + "\\" + filename));
		t.transform(source, result);
	}
	
	
	
	protected abstract void parseDoc(Document doc);
	protected abstract void buildDoc(Document doc);
	
	
	protected void addElementWithText(Element root, String elementName, String elementText, Document doc)
	{
		Element tmp = doc.createElement(elementName);
		tmp.appendChild(doc.createTextNode(elementText));
		root.appendChild(tmp);
	}
	
}
