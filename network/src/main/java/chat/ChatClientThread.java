package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ChatClientThread extends Thread {
	private Socket socket;
	private BufferedReader br;
	
	public ChatClientThread(Socket socket) {
		this.socket=socket;
	}
	
	@Override
	public void run() {		
		try {
			// reader
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			
			while(true) {
				// read
				String data = br.readLine();
				if(data==null) {
					ChatClient.log("closed by server");
					break;
				}
				
				/*
				if(data=="2") {
					//System.out.println(data);
				} else if(data=="3") {
					System.out.println("퇴장하였습니다. 사용해주셔서 감사합니다.");
					break; 
					// 혹은 예외 처리
				}*/
				System.out.println(data);
			}
		} catch (SocketException e) {
			// 퇴장
			System.out.println("퇴장하였습니다. 사용해주셔서 감사합니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
