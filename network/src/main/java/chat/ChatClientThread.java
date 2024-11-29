package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClientThread extends Thread {
	private Socket socket;
	// private BufferedReader br;
	
	public ChatClientThread(Socket socket) {
		this.socket=socket;
	}
	
	@Override
	public void run() {		
		try {
			// reader
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			
			while(true) {
				// read
				String data = br.readLine();
				if(data==null) {
					// closed by server
					ChatClient.log("closed by server");
					break;
				}
				System.out.println(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
