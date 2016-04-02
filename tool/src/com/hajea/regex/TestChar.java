package com.hajea.regex;

import java.io.UnsupportedEncodingException;

//import sun.misc.Unsafe;

public class TestChar {

	//sun.misc.Unsafe unsafe = null;
	 
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		
		// long a = unsafe.allocateMemory(8);
		
		char ch  = '\\'; // �unicode '\u005C\u005C'   _��� '\u005C\' U '\\u005C'
		System.out.println(ch);
		ch = '\u005C\u0027';
		String ch2  =  "\u005D\u005D"  ;//'\u005D'; 
		// \u005C:�H^��:����/\`
		System.out.println(1|3);//1010 100  
		System.out.println(ch2);
		
		System.out.println("\u019b\u0313");
		System.out.println('\u019b');
		System.out.println('\u0313');
		
		System.out.println("\u0041\u030a");
		
		System.out.println('\u0414');
		
		byte [] b = new byte []{0x8,0xa,0x9,0xe};
		String ns = new String(b,"UTF32");
		System.out.println(ns);
		
//		for(int i=0;i<0x1f;i++){
//			System.out.print((char)i + " 	");
//		}
//		Pattern p = Pattern.compile("\\p{javaLowerCase}*"); 
//		Matcher matcher = p.matcher("abc");
//		if(matcher.matches()) {
//			String pathName = matcher.group(0);   
//			System.out.println(pathName);
//		}
	}

 
//			System.out.println(pathName);
//		}
	 

 
 
//		}
	}

 

