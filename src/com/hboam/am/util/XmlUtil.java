package com.hboam.am.util; 

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

/**
 * xml utilities 
 * @author Eric
 */
public class XmlUtil {

	Document document;
	private String xmlns;
	private static String DEFUALT_ENCODING="UTF-8";
	public XmlUtil(String filePath) throws DocumentException, UnsupportedEncodingException{
		SAXReader reader = new SAXReader();
		 this.document = reader.read(filePath);
	}
	
	public XmlUtil(String filePath,String charset,String xmlns) throws DocumentException, UnsupportedEncodingException{
		SAXReader reader = new SAXReader();
		this.xmlns = xmlns;
		 try {
			this.document = reader.read(new ByteArrayInputStream(filePath.getBytes(charset)));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	
	public XmlUtil(String filePath,String xmlns) throws DocumentException{
		SAXReader reader = new SAXReader();
		
		 this.document = reader.read(new File(filePath));
		 this.xmlns = xmlns;
	}
	
	public Element getSingleElement(String xpathString,String xmlns)throws Exception{
		XPath xpath= document.createXPath(xpathString);
		HashMap<String, String> xmlMap = new HashMap<String, String>();
		xmlMap.put("xmlns",	 xmlns);
		xpath.setNamespaceURIs(xmlMap);
		Element element=(Element)xpath.selectSingleNode(document);
		return element;
	}
	
	public Element getSingleElement(String xpathString)throws Exception
	{
		XPath xpath= document.createXPath(xpathString);
		HashMap<String, String> xmlMap = new HashMap<String, String>();
		xmlMap.put("xmlns",	 xmlns);
		xpath.setNamespaceURIs(xmlMap);
		Element element=(Element)xpath.selectSingleNode(document);
		return element;
	}
	
	
	public List<Element> getElements(String xpathString,String xmlns)throws Exception
	{
		XPath xpath= document.createXPath(xpathString);
		HashMap<String, String> xmlMap = new HashMap<String, String>();
		xmlMap.put("xmlns",	 xmlns);
		xpath.setNamespaceURIs(xmlMap);
		List<Element> element=(List<Element>)xpath.selectNodes(document);
		return element;
	}
	

	public List<Element> getElements(String xpathString)throws Exception
	{
		XPath xpath= document.createXPath(xpathString);
		HashMap<String, String> xmlMap = new HashMap<String, String>();
		xmlMap.put("xmlns",	 xmlns);
		
		xpath.setNamespaceURIs(xmlMap);
		List<Element> element=(List<Element>)xpath.selectNodes(document);
		return element;
	}
}
