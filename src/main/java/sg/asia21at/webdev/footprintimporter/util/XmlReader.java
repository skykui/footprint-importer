package sg.asia21at.webdev.footprintimporter.util;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sg.asia21at.webdev.footprintimporter.log.LoggerCreator;

public class XmlReader {
	private DocumentBuilder builder;
	private Document doc;
	
	public XmlReader(){
		DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
		try {
			builder = dFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}		
	}
	
	public void openDoc(Path file) throws SAXException, IOException{
		doc = builder.parse(file.toFile());
	}
	
	public String getString(String key) {
		
		try {
			NodeList nl = doc.getElementsByTagName(key);
			Node keyNode = nl.item(0).getFirstChild();

			return keyNode.getTextContent();

		} catch (Exception e) {
			// e.printStackTrace();
			LoggerCreator.getLogger().error("XML Key is not found.");

			return null;
		}
	}
}
