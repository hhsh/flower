package com.hajea.dir;

import java.io.File;
import java.util.List;

import com.hajea.dir.Directory.TreeInfo;
import com.hajea.file.FileIO;
import com.hajea.path.Path;

public class DirTravel {
	
	public static void main(String[] args) {
		//travelDir();
		//renameFiles();
		
		editJavaCodePackage(new File(Path.sourceCodeRoot+"com/hajea/ref"),"package com.hajea.ref;") ;
		editJavaCodePackage(new File(Path.sourceCodeRoot+"com/hajea/unsafe"),"package com.hajea.unsafe;") ;
		editJavaCodePackage(new File(Path.sourceCodeRoot+"com/hajea/thread"),"package com.hajea.thread;") ;
		editJavaCodePackage(new File(Path.sourceCodeRoot+"com/hajea/loader"),"package com.hajea.loader;") ;
		editJavaCodePackage(new File(Path.sourceCodeRoot+"com/hajea/compiler"),"package com.hajea.compiler;") ;
		editJavaCodePackage(new File(Path.sourceCodeRoot+"com/hajea/compiler/test"),"package com.hajea.compiler.test;") ;
	}
	

	public static void travelDir() {
		String filePath = Path.projectRoot + "file/";
		File startFile = new File(filePath);
		String javaFilter  = "^.*\\.java$";
		String txtFilter  = "^.*\\.txt$";
		TreeInfo tree=Directory.walk(startFile,javaFilter);
		List<File> fileList=tree.files;
		for(int i=0;i<fileList.size();i++) {
			File file = fileList.get(i);
			System.out.println(file.getAbsolutePath()); 
		}
	}
	
	public static void renameFiles() {
		String filePath = Path.projectRoot + "file/";
		File startFile = new File(filePath);
		String javaFilter  = "^.*\\.java$";
		
		TreeInfo tree=Directory.walk(startFile,javaFilter);
		List<File> fileList=tree.files;
		for(int i=0;i<fileList.size();i++) {
			File file = fileList.get(i);
			String srcFile = file.getAbsolutePath();
			String desFile = srcFile.replace(".java", ".text");
			System.out.println(srcFile +  " -> " + desFile );
			FileIO.saveFile(srcFile,desFile);
			//FileIO.saveFileByChannel(srcFile,desFile);
		}
		
	}
	
	public static void editJavaCodePackage(File startFile,String packageName) {
		String javaFilter  = "^.*\\.java$";
		TreeInfo tree=Directory.walk(startFile,javaFilter);
		List<File> fileList=tree.files;
		for(int i=0;i<fileList.size();i++) {
			File file = fileList.get(i);
			String srcFile = file.getAbsolutePath();
			FileIO.replaceFileContent(srcFile,"^\\s*package.*?;.*$",packageName);
			//FileIO.saveFileByChannel(srcFile,desFile);
		}
	}
	
	
}
