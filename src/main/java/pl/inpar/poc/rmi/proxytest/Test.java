package pl.inpar.poc.rmi.proxytest;

import java.lang.reflect.Proxy;

public class Test {
	public static void main(String... args) {
		// @formatter:off
		TestIF t = (TestIF) Proxy.newProxyInstance(
				TestIF.class.getClassLoader(), 
				new Class<?>[] { TestIF.class }, 
				new TestInvocationHandler(
					new TestImpl()
				)
			);
		// @formatter:on
		
		System.out.printf("t.hello(Duke): %s%n", t.hello("Duke"));
		System.out.printf("t.toString(): %s%n", t);
		System.out.printf("t.hashCode(): %H%n", t);
		System.out.printf("t.equals(t): %B%n", t.equals(t));
		System.out.printf("t.equals(new Object()): %B%n", t.equals(new Object()));
		System.out.printf("t.equals(null): %B%n", t.equals(null));
	}
}