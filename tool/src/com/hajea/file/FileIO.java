package com.hajea.file;
 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hajea.path.Path;

/**
 * 修改文件
 */
public class FileIO{

	/*
	 * 读一个文件，并返回字符串
	 */
	
	public static String read(InputStream is ,String charCode) {
		String line = null;		
		StringBuffer strBuf = new StringBuffer();		
		BufferedReader bufReader = null;
		try {
			InputStreamReader isr = new InputStreamReader(is,charCode) ;
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
	public static String read(InputStream is) {
		return read( is, "UTF-8");
	}
	
	public static String read(String fullFilePath,String charCode ) {
		String line = null;		
		StringBuffer strBuf = new StringBuffer();		
		BufferedReader bufReader = null;
		try {
			FileInputStream fis = new FileInputStream(fullFilePath);
			InputStreamReader isr = new InputStreamReader(fis,charCode) ;
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
	public static String read(String fullFilePath) {
		return read(fullFilePath,"UTF-8");
	} 

	/*
	 * 将内容回写到文件
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
	public static void write(String filePath, String content) {
		write(filePath, content,"UTF-8");
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

	/*
	 * 在文件某个位置插入txt
	 */
	public static void replaceFileContent(String fullFilePath, String postionReg,String insertTxt)  {
		String txt =  read(fullFilePath) ;
		Pattern p = Pattern.compile(postionReg,Pattern.MULTILINE); 
		Matcher matcher = p.matcher(txt);
		txt = matcher.replaceFirst(insertTxt); 
		write(fullFilePath,txt);
	}

	
	 /*
	  * 移动文件
	  */
	public static void saveFile(String fullFilePath,String fullsavePath ){
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		byte [] buf = new byte[1024];
		try	{
			bin = new BufferedInputStream(new FileInputStream(fullFilePath));
			bout = new BufferedOutputStream(new FileOutputStream(fullsavePath));
			int numBytes; 
			while ((numBytes = bin.read(buf))!= -1)	{		
				 bout.write(buf,0,numBytes); 
			}
			 bin.close();
		     bout.close();
					 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{ 
			 
		}
		
	}
	/*
	  * 移动文件
	  */
	public static void saveFileByChannel(String fullFilePath,String fullsavePath ) {
		try(FileChannel from = new FileInputStream(fullFilePath).getChannel();
			FileChannel to = new FileOutputStream(fullsavePath).getChannel();){
			to.transferFrom(from, 0, from.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	 
	public static void main(String[] args) throws Throwable { 

		String f1 = "F:/git-repo/java/tool/file/ss/basic/EvenChecker.java";
		String f2 = "F:/git-repo/java/tool/file/ss/basic/EvenChecker.txt";
		//FileIO.saveFileByChannel(f1,f2);
		//FileIO.saveFile(f1,f2);
		
		//replaceFileContent(f1,"^","fuck\n");
		
		
	}

}