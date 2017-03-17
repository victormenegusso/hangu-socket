package hangu.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 
 * @author Victor Menegusso
 *
 */
public class HanguSocketClient extends Thread{
	private Socket socket;

	private InputStream inStream;
	private OutputStream outStream;
	
	private DataInputStream dataInStream;
	private DataOutputStream dataOutStream;
	
	public HanguSocketClient(Socket cliente_socket)
	{
		try
		{
			this.socket = cliente_socket;
			
			this.inStream = this.socket.getInputStream();
			this.outStream = this.socket.getOutputStream();
			
			this.dataInStream = new DataInputStream(inStream);
			this.dataOutStream = new DataOutputStream( outStream );
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		try
		{
			
			System.out.println("process client");
			switchService( parseJSON( dataInStream.readUTF() ) );
			// Encerra cliente //
			this.socket.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private JsonElement parseJSON(String json){
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(json, JsonElement.class);
		return je;
	}
	
	private void switchService(JsonElement jsonElem) throws IOException, InterruptedException{
		
		HanguIO io = new HanguIO();
		JsonObject obj = jsonElem.getAsJsonObject();
		
		switch (obj.get("service").getAsString()) {
		case "read_log":
			System.out.println("read_log");
			io.executeReadLog(socket, dataOutStream, obj.get("path").getAsString());
			break;
		case "execute_script":
			System.out.println("execute_script");
			boolean r;
			r = io.execute(obj.get("cmd").getAsString());
			dataOutStream.writeBoolean(r);
			break;
		case "file_exists":
			System.out.println("file_exists");
			r = io.fileExists(obj.get("path").getAsString());
			dataOutStream.writeBoolean(r);
			break;
		default:
			System.out.println("error");
			break;
		}
	
	}
	
}
