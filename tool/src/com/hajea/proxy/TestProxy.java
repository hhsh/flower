package com.hajea.proxy;

 
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import static java.lang.reflect.Proxy.newProxyInstance;

public class TestProxy {
	private static TestProxy sqlSessionProxy;
	public static void main(String[] args) {
		sqlSessionProxy = (TestProxy) newProxyInstance(
				TestProxy.class.getClassLoader(),
		        new Class[] { TestProxy.class },
		        new SqlSessionInterceptor());
	}

}
class SqlSessionInterceptor implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		return null;
	}
	
}