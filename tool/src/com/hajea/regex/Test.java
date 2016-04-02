package com.hajea.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		String regex = "<img.*?>";
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		Matcher matcher = pattern.matcher("<a href=''><img src =''> </a>");
		StringBuffer sb=new StringBuffer();
		while (matcher.find()) 
		{
			matcher.appendReplacement(sb, "�G");//
		}
		matcher.appendTail(sb);//�>(���b
		System.out.println(sb.toString());
		
//		String str = "The number is 123456789.";
//		String regex = "(?=\\d)(?=(\\d\\d\\d)+$)";  
//		
//		
//		
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(str);
//		while(matcher.find()){
//			System.out.println("match:"+matcher.group(0) + " at " + matcher.start() + "-"+ matcher.end());
//		}

	}

 

//		}

	}

 

