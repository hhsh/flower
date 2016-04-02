package com.hajea.path;


public class Path {
	
	public static String buildPathRoot = Path.class.getClassLoader().getResource("").getPath();
	public static String projectRoot = buildPathRoot.replace("build/classes/", "");
	public static String sourceCodeRoot = projectRoot + "src/";

}
