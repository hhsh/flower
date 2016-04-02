package com.hajea.exception;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.AlreadyConnectedException;
import java.rmi.NotBoundException;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

 

public class TestException {

	public static void main(String[] args)  {
		/*
		 * try { test1(); } catch (NoSuchMethodException | SecurityException |
		 * InstantiationException | IllegalAccessException |
		 * IllegalArgumentException | InvocationTargetException e) {
		 * e.printStackTrace(); }
		 * 
		 * try { test2(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * 
		 * try { try { test5(); } catch (InstantiationException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (IllegalAccessException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } catch (ClassNotFoundException e) {
		 * e.printStackTrace(); }
		 * 
		 * 
		 * test7();
		
		try {
			test8();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
		
		 */
		
		 
		 
	}

	public static void test1() throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<? extends Foo> cls = Bar.class;
		Constructor<? extends Foo> constructor = cls
				.getConstructor(new Class[] { Integer.class, String.class });
		Foo foo = constructor.newInstance(new Object[] { 100, "hello" });
		System.out.println(foo);
	}

	public static void test2() throws IOException {
		ClassLoader loader = Bar.class.getClassLoader();
		String s1 = "org/slf4j/impl/StaticLoggerBinder.class";
		String s2 = "java/lang/Integer.class";
		Enumeration<URL> enu = ClassLoader.getSystemResources(s2);
		while (enu.hasMoreElements()) {
			URL path = (URL) enu.nextElement();
			System.out.println(path.getProtocol());
			System.out.println(path);
		}
	}

	public static void test3() {
		Exception ex1 = new IllegalStateException("make some ex.");
		Exception ex2 = new IllegalStateException("cause ex 2.");
		Exception ex3 = new Exception("cause ex 3.");

		ex2.initCause(ex3);

		ex1.initCause(ex2);
		StringWriter stringWriter = new StringWriter();
		ex1.printStackTrace(new PrintWriter(stringWriter));
		String msg = stringWriter.toString();
		System.out.println(msg);
	}

	public static void test4() {
		if (List.class.isAssignableFrom(ArrayList.class)) {
			System.out.println("list is assignable from arraylist");
		}
		if (List.class.isAssignableFrom(String.class)) {
			System.out.println("list is assignable from String");
		}
	}

	public static void test5() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		String className = "learn.log.Bar";
		Class superClass = Foo.class;
		//Class classObj = Loader.loadClass(className);
//		if (superClass.isAssignableFrom(classObj)) {
//			System.out.println("Foo is assignable from Bar");
//		}
//
//		Bar bar = (Bar) classObj.newInstance();
//		System.out.println("bar:" + bar);

	}

	public static void test6() throws Exception {
		/*
		 * RuntimeException} and its subclasses are <em>unchecked
		 * exceptions</em>. Unchecked exceptions do <em>not</em> need to be
		 * declared in a method or constructor's {@code throws} clause if they
		 * can be thrown by the execution of the method or constructor and
		 * propagate outside the method or constructor boundary.
		 */
		Exception ex1 = new IllegalStateException("IllegalStateException");
		Exception ex2 = new Exception("Exception");
		Exception ex3 = new NotBoundException("NotBoundException");
		Exception ex4 = new ArithmeticException("ArithmeticException");
		throw ex4;
	}

	public static void test7() {
		Class stringClass = String.class;
		ClassLoader stringClsLoader = stringClass.getClassLoader();
		Class integerClass = Integer.class;
		ClassLoader integerClsLoader = integerClass.getClassLoader();
		System.out.println(integerClsLoader == stringClsLoader);

		ClassLoader loader1 = Thread.currentThread().getContextClassLoader();
		ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
		System.out.println(loader1 == loader2);
		Thread th1 = new Thread("th1") {
			public void run() {
				System.out.println("inside th1.");
				Thread thread = Thread.currentThread();
				System.out.println(thread);
				ClassLoader loader3 = thread.getContextClassLoader();
				// sun.misc.Launcher$AppClassLoader@58644d46
				System.out.println(loader2 == loader3);

			}
		};
		th1.start();
	}

	public static void test8() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		Class<?> cls = Class.forName("learn.log.Bar", true, Thread
				.currentThread().getContextClassLoader());
		Bar bar = (Bar) cls.newInstance();
		System.out.println("bar:" + bar);
	}

	public static URL getClassLocation(final Class cls) {
		if (cls == null)
			throw new IllegalArgumentException("null input: cls");
		URL result = null;
		final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
		final ProtectionDomain pd = cls.getProtectionDomain();
		// java.lang.Class contract does not specify if 'pd' can ever be null;
		// it is not the case for Sun's implementations, but guard against null
		// just in case:
		if (pd != null) {
			final CodeSource cs = pd.getCodeSource();
			// 'cs' can be null depending on the classloader behavior:
			if (cs != null)
				result = cs.getLocation();
			if (result != null) {
				// Convert a code source location into a full class file
				// location
				// for some common cases:
				if ("file".equals(result.getProtocol())) {
					try {
						if (result.toExternalForm().endsWith(".jar")|| result.toExternalForm().endsWith(".zip"))
							result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
						else if (new File(result.getFile()).isDirectory())
							result = new URL(result, clsAsResource);
					}
					catch (MalformedURLException ignore) {
						
					}
				}
			}
		}
		if (result == null) {
			// Try to find 'cls' definition as a resource; this is not
			// document��d to be legal, but Sun's implementations seem to //allow
			// this:
			final ClassLoader clsLoader = cls.getClassLoader();
			result = clsLoader != null ? clsLoader.getResource(clsAsResource)
					: ClassLoader.getSystemResource(clsAsResource);
		}
		return result;
	}
	
	public static void test9(){
		try
		{
		  String classLocation = null;
		  String error = null;
		  String className = "org.springframework.web.context.support.XmlWebApplicationContext";

		  classLocation = ""+getClassLocation(Class.forName(className));
		  
		  System.out.println("��" + className + "ʵ���������ļ�λ�ڣ�" +  classLocation);
		 
		}
		catch(Exception e)
		{
		   e.printStackTrace();
		}
	}
	
	//AlreadyConnectedException��IllegalStateException�����࣬���Ƕ�������ʱ�쳣
	//��������ʱ�쳣������throws������ȥ����
	public static void test10() throws AlreadyConnectedException{
		String string = null;
		//string.charAt(2);
		if(true){
			throw new IllegalStateException("IllegalStateException");
		}
		
		//NullPointerException nullPointerException = new NullPointerException("NullPointerException..");
	}
	public static void test11()	{
		//����AlreadyConnectedException������ʱ������˵���test10()���ô����쳣��
		test10();
	}
	
	
	//MyEx �Ǽ���쳣
	//��Ȼthrows������ĳ���쳣�࣬����÷������Ƿ��׳����쳣���޹⡣ֻ�����ø÷���ʱ�йء�
	//����������Ǽ�����쳣������ø���ʱ�����봦���쳣��
	//���ڻ�ͷ��test6()�͸�������ˡ�
	public static void test12() throws MyEx{
		String string = null;
		string.charAt(2);
		if(true){
			throw new IllegalStateException("IllegalStateException");
		}
		
		//NullPointerException nullPointerException = new NullPointerException("NullPointerException..");
	}
	public static void test13()	{
		//����AlreadyConnectedException������ʱ������˵���test10()���ܴ����쳣��
		try {
			test12();
		} catch (MyEx e) {
			e.printStackTrace();
		}
	}
	
	//����쳣�����
	public static void test14()throws MyEx,UrEx	,HerEx{
		
	}
	public static void test15()	{
		try {
			test14();
		} catch (UrEx e) {
			e.printStackTrace();
		}
		catch (HerEx e) {
			e.printStackTrace();
		}
		catch (MyEx e) {
			e.printStackTrace();
		}
	}

}

class Foo {

}

class Bar extends Foo {
	Integer i;
	String s;

	public Bar() {
		i = 10;
		s = "init";
	}

	public Bar(Integer i, String s) {
		this.i = i;
		this.s = s;
	}

	public String toString() {
		return "i = " + i + ",s = " + s;
	}
}

class NetworkClassLoader extends ClassLoader {
	String host;
	int port;

	public Class findClass(String name) {
		byte[] b = loadClassData(name);
		return defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) {
		// load the class data from the connection
		return null;
	}
}

class MyEx extends Exception {
	private static final long serialVersionUID = -9020176689733811327L;

	public MyEx(String name){
		super(name);
	}
}

class UrEx extends MyEx {
	private static final long serialVersionUID = 1L;

	public UrEx(String name){
		super(name);
	}
}
class HerEx extends Exception {
	public HerEx(String name){
		super(name);
	}
}
