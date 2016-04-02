package com.hajea.regex;

import java.io.UnsupportedEncodingException;

public class TestUnicode {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("hello world");
		String s = "I am q";
		toHex(s);
		byte [] bytes = s.getBytes("utf8");
		toHex(bytes);
		bytes = s.getBytes("gbk");
		toHex(bytes);
		bytes = s.getBytes("utf16");
		toHex(bytes);
		bytes = s.getBytes("utf32");
		toHex(bytes);
	}
	
	public static String toHex(byte [] bs){
		String rs = "";
		for(int i=0;i<bs.length;i++){
			byte ch = bs[i];
			int chi = (int)ch ;
			String hex = Integer.toHexString(ch & 0xff);
			rs += hex + " ";
		}
		System.out.println(rs);
		return rs;
	}
	
	public static String toHex(String s){
		char chs[] = s.toCharArray();
		String rs = "";
		for(int i=0;i<chs.length;i++){
			char ch = chs[i];
			int chi = (int)ch;
			String hex = Integer.toHexString(chi);
			rs += hex + " ";
		}
		System.out.println(rs);
		return rs;
	}

}

 

