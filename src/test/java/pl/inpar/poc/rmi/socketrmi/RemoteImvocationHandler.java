package pl.inpar.poc.rmi.socketrmi;


import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteImvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		RequestMethod<Object> requestMethod = null;
		if (args!=null) {
			requestMethod = new RequestMethod<>(method.getDeclaringClass(), method.getName(), method.getParameterTypes(), args);
		} else {
			requestMethod = new RequestMethod<>(method.getDeclaringClass(), method.getName());
		}
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bout);
		objectOutputStream.writeObject(requestMethod);
		objectOutputStream.flush();
		System.out.println(new BASE64Encoder().encode(bout.toByteArray()));
		
		
		String s = new ServerRmi().<String>invoke(requestMethod);
		
		return s;
	}

}
