package com.hajea.file;
 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 修改文件
 */
public class FileIO{

	/*
	 * 读一个文件，并返回字符串
	 */
	
	public static String read(InputStream is ) {
		String line = null;		
		StringBuffer strBuf = new StringBuffer();		
		BufferedReader bufReader = null;
		try {
			InputStreamReader isr = new InputStreamReader(is,"UTF-8") ;
			bufReader = new BufferedReader(isr);
			while ((line = bufReader.readLine()) != null) {			 
				strBuf.append(line);
				strBuf.append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (IOException e) {
					bufReader = null;
				}
			}
		}
		return strBuf.toString();
	}
	public static String read(String fullFilePath ) {
		String line = null;		
		StringBuffer strBuf = new StringBuffer();		
		BufferedReader bufReader = null;
		try {
			FileInputStream fis = new FileInputStream(fullFilePath);
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8") ;
			bufReader = new BufferedReader(isr);
			while ((line = bufReader.readLine()) != null) {			 
				strBuf.append(line);
				strBuf.append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (IOException e) {
					bufReader = null;
				}
			}
		}
		return strBuf.toString();
	}
	
	 /*
	  * 移动文件
	  */
	public static void saveFile(String fullFilePath,String fullsavePath ){
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		byte [] buf=new byte[1024];
		try	{
			bin = new BufferedInputStream(new FileInputStream(fullFilePath));
			int in =bin.read(buf) ;			
			bout=new BufferedOutputStream(new FileOutputStream(fullsavePath));
			while(in!=-1) {
				bout.write(buf);
			}
			bout.close();
			bin.close();			 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	

	/*
	 * 将内容回写到文件�?
	 */
	public static void write(String filePath, String content,String code) {
		BufferedWriter bw = null;
		try {
			bw =   new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath)),code));
			bw.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) 
			{
				try
				{
					bw.close();
				} catch (IOException e)
				{
					bw = null;
				}
			}
		}
	}
	
	public static void write(String parentPath ,String filePath, String content,String code){
		BufferedWriter bw = null;
		File dir= new File(parentPath);
		if(!dir.exists())
		{
			dir.mkdir();
		}
		try
		{
			bw =   new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(parentPath,filePath)),code));
			bw.write(content);
		} catch (Exception e) 
		{
			e.printStackTrace();
		} finally 
		{
			if (bw != null) 
			{
				try
				{
					bw.close();
				} catch (IOException e)
				{
					bw = null;
				}
			}
		}
	}

	public void fileAppender(String fileName, String content)throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = null;
		// �?行一行的�?
		StringBuilder sb = new StringBuilder();
		sb.append(content);
		while ((line = reader.readLine()) != null) {
			sb.append(line);
			sb.append("\r\n");
		}
		reader.close();

		// 写回�?
		RandomAccessFile mm = new RandomAccessFile(fileName, "rw");
		mm.writeBytes(sb.toString());
		mm.close();
	}

	 
	public static void main(String[] args) { 
		 
		String path = "F:\\git-repo\\work_tool\\WORK_TOOL\\src\\com\\hajea\\file\\EAM_Project.java";
		//InputStream is = FileIO.class.getResourceAsStream();
		String rs = read(path);
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