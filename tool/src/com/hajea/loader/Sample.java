package com.hajea.loader;
 
public class Sample {
	public Sample()  {  
		ClassLoader loader = Sample.class.getClassLoader();
        System.out.println("sample is loader by " +loader+",parent loader is "+ loader.getParent() );  
        new Dog() ;  
    }  
}
 
