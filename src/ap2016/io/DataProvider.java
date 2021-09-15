package ap2016.io;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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


/**
 * This class model a generic data provider that can read a file of XML entries and store its content in a list. The values of the list can then be written
 * back to the file. This class also models the possibility to import data to the current list from another file.
 * @author Matteo Nardini
 *
 * @param <T> The type of data that will be read from the file.
 */
public abstract class DataProvider<T>
{
	/**
	 * Utility method that allows to create an XML tag with a certain text in it and to append this newly generated node to another node.
	 * @param root The node to which the routine appends the newly generated node.
	 * @param elementName The name of the tag of the newly generated node.
	 * @param elementText The text contained in the newly generated tag.
	 * @param doc The XML document being used.
	 */
	protected static void addElementWithText(Element root, String elementName, String elementText, Document doc)
	{
		Element tmp = doc.createElement(elementName);
		tmp.appendChild(doc.createCDATASection(elementText != null ? elementText : ""));
		root.appendChild(tmp);
	}

	/**
	 * The list that will contain the entries read from the file.
	 */
	protected ArrayList<T> data;

	/**
	 * The name of the main file from which to read and in which to save the entries.
	 */
	protected String filename;

	/**
	 * Creates a new data provider.
	 * @param filename The name of the main file from which to read and in which to save the entries.
	 */
	protected DataProvider(String filename)
	{
		this.data = new ArrayList<T>();
		this.filename = filename;
	}

	/**
	 * This method is called after reading an external file which data have to be imported in the current {@link #data} list. It is responsible for using the XML Document to add the new data to the list {@link #data}.
	 * @param doc The XML document containing the content of the external file.
	 */
	protected abstract void appendDoc(Document doc);

	/**
	 * This method is called before saving the content of the list {@link #data} to the file and is responsible for using the {@link #data} list entries to generate an XML document to be written in the file.
	 * @param doc The XML document that will be written to the file.
	 */
	protected abstract void buildDoc(Document doc);

	/**
	* Allows to access the data read from the file. If the method {@link #readDataFromFile() readDataFromFile} has not been called yet, the returned list will be empty.
	* @return A list of the entries read from the file.
	*/
	public ArrayList<T> getData()
	{
		return this.data;
	}

	/**
	 * This method is called after reading the entire file content and is responsible for using the XML Document to fill the list {@link #data}.
	 * @param doc The XML document containing the content of the file.
	 */
	protected abstract void parseDoc(Document doc);

	/**
	 * Clears all the data currently contained in the list and reads and parses the content of the file located at {@link ap2016.application.ApplicationConstants#dataBase ApplicationConstants.dataBase}\{@link #filename}.
	 * @throws Exception If the given file is inaccessible, inexistent, invalid.
	 */
	public void readDataFromFile() throws Exception
	{
		this.data.clear();
		try
		{

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(Paths.get(ApplicationConstants.dataBase, this.filename).toFile());
			parseDoc(doc);

		} catch (IOException | ParserConfigurationException e)
		{
			throw new Exception("The file located at \""
					+ Paths.get(ApplicationConstants.dataBase, this.filename).toString() + "\" is not valid");
		} catch (SAXException e)
		{
		}
	}

	/**
	 * This method allows to import XML data from another file to the current list.
	 * @param f The file to import.
	 * @throws Exception If the given file is inaccessible, inexistent, invalid.
	 */
	public void readDataFromSelectedFile(File f) throws Exception
	{
		try
		{

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
			appendDoc(doc);

		} catch (IOException | ParserConfigurationException e)
		{
			throw new Exception("The file located at \"" + f.getPath() + "\" is not valid");
		} catch (SAXException e)
		{
		}
	}

	/**
	  * This method deletes the content of the file located at {@link ap2016.application.ApplicationConstants#dataBase ApplicationConstants.dataBase}\{@link #filename} and writes each entry of the list {@link #data} to it.
	  */
	public void saveDataToFile()
	{
		Document doc = null;
		try
		{
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			buildDoc(doc);

		} catch (ParserConfigurationException e)
		{
		}

		Transformer t;
		try
		{
			t = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(Paths.get(ApplicationConstants.dataBase, this.filename).toFile());
			t.transform(source, result);
		} catch (TransformerFactoryConfigurationError | TransformerException e)
		{
		}
	}

}
