package com.hajea.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import org.apache.crimson.tree.XmlDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class DomDemo {
	
	private static String xmlFilsDir = DomDemo.class.getClassLoader().getResource("").getPath() +"xmlFiles/";
     
    public static void queryXml(){
    	
        try{        	
        	
        	
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();            
            Document doc = docBuilder.parse(xmlFilsDir +"school.xml");
            System.out.println("处理该文档的DomImplementation对象  = "+ doc.getImplementation());
            NodeList nList = doc.getElementsByTagName("Student");           
            for(int i = 0; i< nList.getLength() ; i ++){
                Element node = (Element)nList.item(i);
                System.out.println("Name: "+ 	node.getElementsByTagName("Name").		item(0).getFirstChild().getNodeValue());
                System.out.println("Num: "+ 	node.getElementsByTagName("Num").		item(0).getFirstChild().getNodeValue());
                System.out.println("Classes: "+ node.getElementsByTagName("Classes").	item(0).getFirstChild().getNodeValue());
                System.out.println("Address: "+ node.getElementsByTagName("Address").	item(0).getFirstChild().getNodeValue());
                System.out.println("Tel: "+ 	node.getElementsByTagName("Tel").		item(0).getFirstChild().getNodeValue());
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void insertXml(){
        Element school = null;
        Element student = null;
        Element name = null;
        Element num = null;
        Element classes = null;
        Element address = null;
        Element tel = null;
        try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlFilsDir+"school.xml");
            NodeList nList = doc.getElementsByTagName("School");
            school = (Element)nList.item(0);
            student = doc.createElement("Student");
            student.setAttribute("examId", "23");
            name = doc.createElement("Name");
            name.appendChild(doc.createTextNode("香香"));
            student.appendChild(name);
            
            num = doc.createElement("Num");
            num.appendChild(doc.createTextNode("1006010066"));
            student.appendChild(num);
            
            classes = doc.createElement("Classes");
            classes.appendChild(doc.createTextNode("眼视光5"));
            student.appendChild(classes);
            
            address = doc.createElement("Address");
            address.appendChild(doc.createTextNode("浙江温州"));
            student.appendChild(address);
            
            tel = doc.createElement("Tel");
            tel.appendChild(doc.createTextNode("123890"));
            student.appendChild(tel);
            
            school.appendChild(student);
            
           // ((XmlDocument)doc).write(new FileOutputStream("src/xidian/sl/dom/insertSchool.xml"));
            System.out.println("成功");
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }    
    }
    public static void main(String[] args){
        //读取
        DomDemo.queryXml();
        //插入
       // DomDemo.insertXml();
    }
}