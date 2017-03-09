package hangu.socket;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

/**
 * 
 * @author victor menegusso
 *
 */
public class HanguServer {

	public static void main(String[] args) {

		System.out.println("Start socket on the port 5000");

		/*
		try {
			ServerSocket ss = new ServerSocket(5000);

			while (true) {
				System.out.println("wait client...");
				Socket s = ss.accept();
				//s.setk
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
		*/
		System.out.println( pingURL("http://www.google.com.br", 2000) );
		System.out.println( pingURL("http://alivepro.com.br/", 2000) );
		System.out.println( pingURL("http://gerador.alivepro.com.br/webservice_alive/AliveWS?wsdl", 2000) );
		
	}
	
	/**
	 * Pings a HTTP URL. This effectively sends a HEAD request and returns <code>true</code> if the response code is in 
	 * the 200-399 range.
	 * @param url The HTTP URL to be pinged.
	 * @param timeout The timeout in millis for both the connection timeout and the response read timeout. Note that
	 * the total timeout is effectively two times the given timeout.
	 * @return <code>true</code> if the given HTTP URL has returned response code 200-399 on a HEAD request within the
	 * given timeout, otherwise <code>false</code>.
	 */
	public static boolean pingURL(String url, int timeout) {
	    url = url.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.

	    try {
	        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	        connection.setConnectTimeout(timeout);
	        connection.setReadTimeout(timeout);
	        connection.setRequestMethod("HEAD");
	        int responseCode = connection.getResponseCode();
	        return (200 <= responseCode && responseCode <= 399);
	    } catch (IOException exception) {
	        return false;
	    }
	}

}
