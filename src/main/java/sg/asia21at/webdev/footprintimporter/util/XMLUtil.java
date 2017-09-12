package sg.asia21at.webdev.footprintimporter.util;

import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sg.asia21at.webdev.footprintimporter.log.LoggerCreator;

public class XMLUtil {
	private static final String configPath = "config.xml";
	private static final Resource resource = new ClassPathResource(configPath);
	private static final Document doc = readXmlDoc();

	private static Document readXmlDoc() {
		DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = dFactory.newDocumentBuilder();
			return builder.parse(resource.getFile());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			LoggerCreator.getLogger().error("Fail to load config.xml file.");
			return null;
		} catch (SAXException e) {
			LoggerCreator.getLogger().error("Fail to load config.xml file.");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			LoggerCreator.getLogger().error("Fail to load config.xml file.");
			e.printStackTrace();
			return null;
		}

	}

	public static String getString(String key, String defValue) {
		String value = defValue;
		try {
			// Get config value node by key
			NodeList nl = doc.getElementsByTagName(key);
			Node keyNode = nl.item(0).getFirstChild();
			// value = keyNode.getNodeValue();
			value = keyNode.getTextContent();
			return value;

		} catch (Exception e) {
			// e.printStackTrace();
			LoggerCreator.getLogger().error("Key: " + key + " is not found. Use default value: " + defValue);

			return defValue;
		}
	}

	public static Boolean getBoolean(String key, Boolean defValue) {
		Boolean value = defValue;
		try {
			value = Boolean.parseBoolean(getString(key, defValue.toString()));
			return value;
		} catch (Exception e) {
			// e.printStackTrace();
			LoggerCreator.getLogger().error("Error. Use default value: " + defValue);
			return defValue;
		}
	}

	public static int getInt(String key, int defValue) {
		int value = defValue;
		try {
			value = Integer.parseInt(getString(key, Integer.toString(defValue)));
			return value;
		} catch (Exception e) {
			// e.printStackTrace();
			LoggerCreator.getLogger().error("Error. Use default value: " + defValue);
			return defValue;
		}
	}

	public static int[] getIntArray(String key, int[] defValue) {
		int[] value = defValue;
		try {
			// Get config value node by key
			NodeList nl = doc.getElementsByTagName(key);
			Node keyNode = nl.item(0).getFirstChild();
			// value = keyNode.getNodeValue();
			value = Arrays.stream(keyNode.getTextContent().split(",")).map(String::trim).mapToInt(Integer::parseInt)
					.toArray();
			return value;

		} catch (Exception e) {
			// e.printStackTrace();
			LoggerCreator.getLogger().error("Key: " + key + " is not found. Use default value");
			return defValue;
		}
	}

	public static String[] getStringArray(String key, String[] defValue) {
		String[] value = defValue;
		try {
			// Get config value node by key
			NodeList nl = doc.getElementsByTagName(key);
			Node keyNode = nl.item(0).getFirstChild();
			// value = keyNode.getNodeValue();
			value = keyNode.getTextContent().split(",");
			return value;

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
			LoggerCreator.getLogger().error("Key: " + key + " is not found. Use default value");
			return defValue;
		}
	}

	// This method is to extract class name from the .xml configuration file and
	// return a instance

//	public static Object getBean() {
//
//		try {
//			// Get class name node
//			NodeList nl = doc.getElementsByTagName("className");
//			Node classNode = nl.item(0).getFirstChild();
//			String cName = classNode.getNodeValue();
//
//			// Use the class name to create an instance
//			Class c = Class.forName(cName);
//			Object obj = c.newInstance();
//			return obj;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
