package pl.inpar.poc.rmi.socketrmi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ServerRmi {

	
    public <T> T invoke(RequestMethod<Object> requestMethod) {
		
		ObjectOutputStream oos = null;
        try {
	        oos = new ObjectOutputStream(new FileOutputStream(new File("/tmp/obj")));
	        oos.writeObject(requestMethod);
        } catch (IOException e) {
	        e.printStackTrace();
        } finally {
        	try {
	            oos.close();
            } catch (IOException e) {
	            e.printStackTrace();
            }
        }
//		ResponseObject resp = inStream.readObject();
	    return null;
    }

}
