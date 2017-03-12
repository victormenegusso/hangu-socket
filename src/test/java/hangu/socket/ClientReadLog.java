package hangu.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientReadLog {
	
	
	public static void main(String[] args) {
		
		try{
			System.out.println("Start client");
			
			Socket socketClient = new Socket("localhost",5000);
			
			InputStream inputStream = socketClient.getInputStream();
			OutputStream outputStream = socketClient.getOutputStream();
			
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			DataOutputStream dataOutInputStream = new DataOutputStream(outputStream);	
			
			
			String json="{\"service\":\"read_log\",\"path\":\"/home/victor/Downloads/apache-tomcat-8.5.11/logs/catalina.out\"}";
			
			dataOutInputStream.writeUTF(json);
			
			
			//socketClient.close();
			Thread.sleep(10000);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
