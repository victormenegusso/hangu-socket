package hangu.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Victor Menegusso
 *
 */
public class HanguServer {

	public static void main(String[] args) {

		System.out.println("Start Hangu Socket on the port 5000");

		try {
			
			ServerSocket ss = new ServerSocket(5000);

			while (true) {
				System.out.println("wait client...");
				Socket s = ss.accept();
				
				try {
					HanguSocketClient hanguSC = new HanguSocketClient(s);
					hanguSC.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
