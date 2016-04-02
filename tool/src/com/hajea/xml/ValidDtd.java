package com.hajea.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ValidDtd {
	private static String xmlFilsDir = DomDemo.class.getClassLoader().getResource("").getPath() +"xmlFiles/";
	private static String dtdFilsDir = DomDemo.class.getClassLoader().getResource("").getPath() +"dtdFiles/";
    
	public static void main(String[] args) throws Exception {
		 Element  root = null;
		 DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		 docBuilderFactory.setValidating(true);
         
         DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
         docBuilder.setErrorHandler(new MyXmlHandler());
         Document doc = docBuilder.parse(new File(xmlFilsDir+"Stock3.xml"));
          
	}
}
