package pl.inpar.poc.rmi.socketrmi;

import pl.inpar.poc.rmi.proxytest.AuditProxy;
import pl.inpar.poc.rmi.shared.Hello;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.rmi.RemoteException;

import org.junit.Test;

public class TestRequest {
	
	@Test
	public void prerpareFile() throws NoSuchMethodException, SecurityException, IOException {
		
		Hello newProxyInstance = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[] {Hello.class}, new RemoteImvocationHandler());
		newProxyInstance.sayHello();
		
	}
	
	@Test
	public void invokteFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectInputStream oos = new ObjectInputStream(new FileInputStream(new File("/tmp/obj")));
		
		@SuppressWarnings("unchecked")
        RequestMethod<String> requestMethod = (RequestMethod<String>) oos.readObject();
		
		
//		RequestMethod<String> requestMethod = new RequestMethod<String>(Hello.class, "sayHello");
		
		ObjectOutputStream dataOutputStream = new ObjectOutputStream(System.out);
		dataOutputStream.writeObject(requestMethod);
		
		Hello h = (Hello) AuditProxy.newInstance(new Hello() {
			@Override
			public String sayHello() throws RemoteException {
				System.out.println("Called");
				return "hopsasa";
			}
		});
		
		
		String ret = requestMethod.invoke(h);
		System.out.println("RET:"+ret);
		
		
		oos.close();
		
	}
	
}
