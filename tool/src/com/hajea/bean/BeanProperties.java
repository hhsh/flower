package com.hajea.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hajea.entity.Project;
import com.hajea.file.FileIO;
import com.hajea.path.Path;

public class BeanProperties {

	public static void main(String[] args) throws Throwable {
		Class<? extends Object> type = Project.class;
	 
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] pdArr = beanInfo.getPropertyDescriptors();
		
		String s ="";
		for (PropertyDescriptor descriptor : pdArr) {
			Class<?> tCls = descriptor.getPropertyType();
			String pName = descriptor.getName();
			if (pName.equals("class")) 
				continue;
			System.out.println(pName+":"+ tCls.getName());
			
			String rs = String.format("<td data-name=\"%s\" class=\"view-td\" colspan=\"3\"/>%s",pName,tCls.getName());
			System.out.println(rs);

		}

	}
	
	public static void introBeanByReg() { 
		String path = Path.sourceCodeRoot  + "com/hajea/entity/Project.java";
		 
		String rs = FileIO.read(path);
		//System.out.println(rs);
		
		//String regex = "^\\s*private(.*)$";
		String regex = "^\\s*private\\s*(\\w+)\\s*(\\w+)\\s*;\\s*//\\s*(\\S+)\\s*$";
		
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(rs);
		while (matcher.find()) {
			String gp1 = matcher.group(1);
			String gp2 = matcher.group(2);
			String gp3 = matcher.group(3);
			System.out.println(gp1 + ":" + gp2 + ":" + gp3);
		}
		
		
	}

	

}


		