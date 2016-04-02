package com.hajea.xml;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;

public class ValidSchema {
	private static String xmlFilsDir = DomDemo.class.getClassLoader().getResource("").getPath() +"xmlFiles/";
	private static String xsdFilsDir = DomDemo.class.getClassLoader().getResource("").getPath() +"xsdFiles/";
	public static void main(String[] args) throws Exception{
		 SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		 Schema schema = schemaFactory.newSchema(new File(xsdFilsDir+"first.xsd"));

		 DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		 docBuilderFactory.setNamespaceAware(true);
		 docBuilderFactory.setSchema(schema);
		 
		 DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
         docBuilder.setErrorHandler(new MyXmlHandler());
         Document doc = docBuilder.parse(new File(xmlFilsDir+"mySchema.xml"));
         
	}

}
