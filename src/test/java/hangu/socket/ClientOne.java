package hangu.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientOne {
	
	public static void main(String[] args) {
		
		try{
			System.out.println("Começando o cliente");
			//  Variaveis //
			Socket socket_cliente = new Socket("localhost",5000);
			
			InputStream input_stream = socket_cliente.getInputStream();
			OutputStream output_stream = socket_cliente.getOutputStream();
			
			DataInputStream buffer_entrada = new DataInputStream(input_stream);
			DataOutputStream buffer_saida = new DataOutputStream(output_stream);	
			
			
			while(true){
				System.out.print( buffer_entrada.readUTF());
			}
			
			//Thread.sleep(20000);
			//socket_cliente.close();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
