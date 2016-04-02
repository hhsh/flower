package com.hajea.xml;

import java.io.File;
import java.io.FileReader;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLReaderTest {

	public static void main(String[] args) throws Exception {
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(new MyDefaultHandler());
		File file = new File(Constants.xmlFilsDir + "saxXmlReader.xml");
		FileReader fileReader = new FileReader(file);
		reader.parse(new InputSource(fileReader));

	}

}

class MyDefaultHandler extends DefaultHandler {

	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		System.out.println("--------------startElement开始执行--------------------------");
		System.out.println("uri:::" + uri);
		System.out.println("localName:::" + localName);
		System.out.println("qName:::" + qName);
		for (int i = 0; i < attributes.getLength(); i++) {
			System.out.println(attributes.getQName(i) + "-----" + attributes.getValue(i));
		}
		System.out.println("------------------startElement执行完毕---------------------------");
	}

}
