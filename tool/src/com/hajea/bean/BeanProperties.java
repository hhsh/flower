package com.hajea.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.Date;

import com.hajea.entity.Project;

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

}


		