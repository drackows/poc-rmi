package pl.inpar.poc.rmi.socketrmi;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestMethod<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Class<?> clazz;
	private String name;
	private Class<?>[] types;
	private Serializable[] args;

	public RequestMethod(Class<?> clazz, String name, Class<?>[] types, Serializable... args) {
		this.clazz = clazz;
		this.name = name;
		this.types = types;
		this.args = args;
	}

	public RequestMethod(Class<?> clazz, String name, Serializable... args) {
		this.clazz = clazz;
		this.name = name;
		this.types = new Class<?>[0];
		this.args = args;
	}
	
	@SuppressWarnings("unchecked")
    public T invoke(Object obj) {
		if (!getClazz().isAssignableFrom(obj.getClass())) {
			throw new RuntimeException();
		}
		try {
	        Method method = obj.getClass().getMethod(getName(), getTypes());
	        return (T) method.invoke(obj, (Object[])getArgs());
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	        e.printStackTrace();
        }
		return (T) obj;
		
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?>[] getTypes() {
		return types;
	}

	public void setTypes(Class<?>[] types) {
		this.types = types;
	}

	public Serializable[] getArgs() {
		return args;
	}

	public void setArgs(Serializable[] args) {
		this.args = args;
	}
	
}
