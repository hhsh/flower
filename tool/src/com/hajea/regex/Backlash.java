package com.hajea.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Backlash {

	public static void main(String[] args) {
		//http://bbs.csdn.net/topics/240042395
		//)(UTF-8v-  : &#3599 N : &#3662 
		// 3599 = 0x0EOF 
		//U+4E00~U+9FA5
		char nvl  = '\u0000';	
		
		char ch  = '\'';	
		System.out.println(ch);
		ch  = '\u005c\u0027';
		System.out.println(ch);
		ch  = '\u005c'';
		System.out.println(ch);
		 
	    
	    ch  = '\u005c\u006e';
	    System.out.println(ch);
	    String ss  = "\u005c\u0027";
	    System.out.println(ss.length());
	    
		/* Source strings :
		 * 
		 * original text:
		 * We need "char like \" or other escape char " in the text
		 * 
		 * java string literal :
		 * "We need \"char like \\\" or other escape char \" in the text"
		 * 
		 * Regular expressions:
		 * 
		 * original text:
		 * ^[^"]*?"((\\.|[^"])*)"[^"]*$
		 * 
		 * java string literal :
		 * "^[^\"]*?\"((\\\\.|[^\"])*)\"[^\"]*$"
		 */
	    
	    ch  = '\n';
	    System.out.println((int)ch);
	    
	    System.out.print("hello");
	    System.out.print((char)0x000A);
	    System.out.println("world");
		
		String r = "\\\\\""; 	// [\\]
		String s = "\\\""; 		// [\,"]
		Pattern p = Pattern.compile(r);
		Matcher m = p.matcher(s);
		if(m.matches()){
			System.out.println(m.group(0));
		}
		
		//String s = "\u0E0F\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e\u0e4e";
		//System.out.println(s);
		
//		char ch = (char)0x0bc7;
//		 
//		Character.isLowerCase(ch);
//		System.out.print((int)'');
//		
//		for(int i=0x0;i<0x0e4f;i++){
//			ch = (char)i;
//			System.out.print(ch );
//			if(i%10000000==0)
//				System.out.println();
//		}
		
	

	}

 
//		}
		
	

	}

 

