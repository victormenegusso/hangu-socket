package hangu.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientExecute {
	
	
	public static void main(String[] args) {
		
		try{
			System.out.println("Start client");
			
			Socket socketClient = new Socket("localhost",5000);
			
			InputStream inputStream = socketClient.getInputStream();
			OutputStream outputStream = socketClient.getOutputStream();
			
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			DataOutputStream dataOutInputStream = new DataOutputStream(outputStream);	
			
			
			String json="{\"service\":\"execute_script\",\"cmd\":\"/home/victor/Downloads/apache-tomcat-8.5.11/bin/startup.sh\"}";
			
			dataOutInputStream.writeUTF(json);
			System.out.println( dataInputStream.readBoolean() );
			
			socketClient.close();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
