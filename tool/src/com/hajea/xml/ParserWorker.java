package com.hajea.xml;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class ParserWorker {

	public static void main(String[] args)throws Exception {
		XMLReader reader = XMLReaderFactory.createXMLReader();
		WorkerDefaultHandler handler = new WorkerDefaultHandler();
		reader.setContentHandler(handler);
		File file = new File(Constants.xmlFilsDir + "worker.xml");
		FileReader fileReader = new FileReader(file);
		reader.parse(new InputSource(fileReader));
		Worker wk = handler.getWorker();
	}

}

class WorkerDefaultHandler extends DefaultHandler {
	private Worker worker ;
	private List<Worker> list;
	private boolean finished ;
	private String cont ;
	public WorkerDefaultHandler(){
		super();		
		this.finished = false;
		this.list = new ArrayList<Worker>(2);
	}
	public void characters (char ch[], int start, int length) throws SAXException    {	
		String cont = new String(ch, start, length).trim();
		this.cont = cont;
	}
	public void startDocument () throws SAXException  {
		System.out.println("开始解析文档");
	}
	public void endDocument () throws SAXException  {
		System.out.println("结束解析文档");
		this.finished = true;
		for(int i=0;i<this.list.size();i++){
			System.out.println(this.list.get(i));
		}
		
	}
	public void startElement (String uri, String localName,String qName, Attributes attributes) throws SAXException{
		System.out.println("开始解析元素:"+localName);
		if(localName.equals("worker")){
			this.worker = new Worker();
		}
		
	}
	public void endElement (String uri, String localName, String qName)throws SAXException{
		if(localName.equals("name")){
			this.worker.setName(cont);
		}else if(localName.equals("score")){
			this.worker.setScore(Float.parseFloat(cont));
		}else if(localName.equals("age")){
			this.worker.setAge(Integer.parseInt(cont));
		}else if(localName.equals("sex")){
			this.worker.setSex(cont);
		}else if(localName.equals("marry")){
			this.worker.setMarry(Boolean.parseBoolean(cont));
		}
		if(localName.equals("worker")){
			this.list.add(this.worker);
		}
	}
	        
	public Worker getWorker() {
		return worker;
	}	  
}


class Worker{
	private String name;
	private float score;
	private int age;
	private String sex;
	private boolean marry;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public boolean isMarry() {
		return marry;
	}
	public void setMarry(boolean marry) {
		this.marry = marry;
	}
	public String toString(){
		return super.toString() + ":" + this.name +":"+this.sex + ":"+this.age +":"+ this.score + ":" +this.marry;
	}
}