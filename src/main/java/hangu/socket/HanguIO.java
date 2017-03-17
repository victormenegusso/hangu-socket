package hangu.socket;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author Victor Menegusso
 *
 */
public class HanguIO {

	public void executeReadLog(Socket socket, DataOutputStream dataOutStream, String path) throws IOException {

		FileInputStream fileInStream;
		BufferedInputStream reader;

		fileInStream = new FileInputStream(path);
		reader = new BufferedInputStream(fileInStream);

		boolean running = true;
		while (running) {

			if (reader.available() > 0) {

				byte[] contents = new byte[1024];

				int bytesRead = 0;
				while ((bytesRead = reader.read(contents)) != -1) {
					//System.out.println(new String(contents, 0, bytesRead));
					dataOutStream.writeUTF(new String(contents, 0, bytesRead));
				}
			} else {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ex) {
					running = false;
				}
			}
		}

		reader.close();
	}

	public boolean execute(String cmd) throws IOException, InterruptedException{
		ProcessBuilder pBuilder = new ProcessBuilder(cmd);
		Process process = pBuilder.inheritIO().start();
		process.waitFor();
		
		return process.exitValue() == 0 ? true : false;
	}

	public boolean fileExists(String filePath){
		File f = new File(filePath);

		if(f.exists())
			return true;
 
		return false;
	}

}
