package com.hajea.dir;

import java.io.File;
import java.util.List;

import com.hajea.dir.Directory.TreeInfo;
import com.hajea.path.Path;

public class DirTravel {

	public static void main(String[] args) {
		String filePath = Path.projectRoot + "file/";
		File startFile = new File(filePath);
		TreeInfo tree=Directory.walk(startFile);
		List<File> fileList=tree.files;
		for(int i=0;i<fileList.size();i++) {
			File file = fileList.get(i);
			System.out.println(file.getAbsolutePath()); 
		}
		
	}
	
	
}
