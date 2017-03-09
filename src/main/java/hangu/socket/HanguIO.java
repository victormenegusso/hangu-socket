package hangu.socket;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author victor menegusso
 *
 */
public class HanguIO {

	public static void tail(Socket socket, DataOutputStream dataOutStream)	 throws IOException {
		
		
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream( "/home/victor/Downloads/apache-tomcat-8.5.11/logs/catalina.out" ) );
		
		boolean running = true;
		StringBuilder numeros = new StringBuilder();
	    while( running ) {
	    	
	        if( reader.available() > 0 ) {
	        	
	        	numeros = new StringBuilder();
	        	byte[] contents = new byte[1024];

	        	int bytesRead = 0;
	        	while((bytesRead = reader.read(contents)) != -1) { 
	        		//numeros.append(new String(contents, 0, bytesRead));    
	        		System.out.println(new String(contents, 0, bytesRead));
	        		dataOutStream.writeUTF(new String(contents, 0, bytesRead)); 
	        	}
	        	//System.out.println(numeros.toString());
        		//dataOutStream.writeUTF(numeros.toString());

	        	
	        	//System.out.print( (char)reader.read() );
	        	//numeros.append( (char)reader.read() );
	        	//dataOutStream.writeUTF( ((char)reader.read())+"");
	        	//dataOutStream.writeChar(reader.read());
	        }
	        else {
	        	
	        	/*
	        	if(numeros.toString().length()!= 0){
	        		dataOutStream.writeUTF(numeros.toString());
	        	
	        		numeros = new StringBuilder();
	        	}
	        	*/
	        	
	            try {
	                Thread.sleep( 5000 );
	                /*
	                System.out.println( socket.isClosed() );
	                System.out.println( socket.isBound() );
	                System.out.println( socket.isConnected() );
	                System.out.println( socket.isInputShutdown() );
	                //System.out.println( socket.getInputStream().read() );
	                System.out.println( "------------------------------------------------------" );
	                */
	            }
	            catch( InterruptedException ex ) {
	                running = false;
	            }
	        }
	    }
	}
	
}
