package com.hajea.loader;
 
public class Dog {
	public Dog()   
    {  
        ClassLoader loader = Sample.class.getClassLoader();
        System.out.println("dog is loader by " +loader+",parent loader is "+ loader.getParent() );  
    }  
}
  
