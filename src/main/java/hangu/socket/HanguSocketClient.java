package hangu.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 * @author victor menegusso
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
			
			System.out.println("processando");
			HanguIO.tail(socket, dataOutStream);
			// Encerra cliente //
			this.socket.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
