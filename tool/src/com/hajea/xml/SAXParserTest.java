package com.hajea.xml;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class SAXParserTest {
	private static String xmlFilsDir = DomDemo.class.getClassLoader().getResource("").getPath() +"xmlFiles/";
    
	public static void main(String[] args) throws Exception {
		 SAXParserFactory saxParserfactory = SAXParserFactory.newInstance();
          SAXParser saxParser = saxParserfactory.newSAXParser();
          SaxParserHandler handler = new SaxParserHandler();  
          saxParser.parse(xmlFilsDir+"sax.xml",handler);  
	}

}


class SaxParserHandler extends DefaultHandler{
	 boolean isOk = false;  
    public SaxParserHandler() {
        super();
    } 
    public void startElement(String uri,String localName,String qName,Attributes attr){  
       if(attr.getLength()>0){  
           for(int i = 0;i<attr.getLength();i++){  
               System.out.println(attr.getQName(i)+" = "+attr.getValue(i));  
           }  
       }  
       if ("sex".equals(qName)) {  
           isOk = true;  
       }
    }  
    
    public void characters(char[] ch, int start, int length)  throws SAXException {         
        super.characters(ch, start, length);           
        if (isOk) {  
            System.out.println(new String(ch, start, length));  
        }  
    }  
   
    public void endElement(String uri, String localName, String qName)  throws SAXException {  
        super.endElement(uri, localName, qName);  
        if ("sex".equals(qName)) {  
            isOk = false;  
        }  
    }  
     
 }