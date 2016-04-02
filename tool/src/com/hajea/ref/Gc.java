package com.hajea.ref;

import java.lang.ref.WeakReference;

public class Gc {

	public static void main(String[] args) {
		  Object referent = new Object();  
		    WeakReference<Object> weakRerference = new WeakReference<Object>(referent);  
		  
		    assert referent== weakRerference.get() ;  
		      
		    referent = null;  
		  //  System.gc();  
		      
		    /** 
		     * 一旦没有指向 referent 的强引用, weak reference 在 GC 后会被自动回收 
		     */  
		    assert weakRerference.get()==null;  
	}

}
