package com.hajea.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseNS {
	private static String xmlFilsDir = DomDemo.class.getClassLoader().getResource("").getPath() +"xmlFiles/";
    
	public static void main(String[] args) throws Exception {
		try{
			 Element  root = null;
			 DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			 docBuilderFactory.setNamespaceAware(true);
			 docBuilderFactory.setIgnoringElementContentWhitespace(true);
	         
	         DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	         docBuilder.setErrorHandler(new MyXmlHandler());
	         Document doc = docBuilder.parse(new File(xmlFilsDir+"notesNs.xml"));
	         root = doc.getDocumentElement();
	         
	         NodeList nodeList = root.getElementsByTagNameNS("http://www.peter.cn/travel", "book");
	         for(int i=0;i<nodeList.getLength();i++){
	        	Node bookTravel = nodeList.item(i);
	        	NodeList bookTravelChild = bookTravel.getChildNodes();
	        	for(int j=0;j<bookTravelChild.getLength();j++){
	        		Node node = bookTravelChild.item(j);
	        		if(node.getNodeType()==Node.TEXT_NODE) continue;
	        		//String nodeName = node.getLocalName().trim();
	        		String nodeName = node.getNodeName().trim();
	        		String nodeText = node.getTextContent();
	        		 System.out.printf("\n%12s:%s",nodeName,nodeText);
	        	}
	        	 System.out.println();
	         }
			
		}catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		 
	}
}


class MyXmlHandler extends DefaultHandler{
	public void warning (SAXParseException e) throws SAXException{
		 System.out.println("这是一般警告："+e.getMessage());
	}
	 public void error (SAXParseException e) throws SAXException{
		 System.out.println("这是一般错误："+e.getMessage());
	 }
	 public void fatalError (SAXParseException e) throws SAXException{
		 System.out.println("这是致命错误："+e.getMessage());
	 }
		   
		   
}