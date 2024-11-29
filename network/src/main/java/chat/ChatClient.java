package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Base64;
import java.util.Scanner;

import echo.EchoServer;

public class ChatClient {
	public static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;
		
		try {
			scanner = new Scanner(System.in);
			socket = new Socket();
			
			socket.connect(new InetSocketAddress(SERVER_IP,EchoServer.PORT));
			
			// writer
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"), true); // autoFlush
			
			// reader
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			
			// join
			System.out.print("닉네임>>"); // 띄어쓰기 가능
			String nickname = scanner.nextLine();
			// Base64 인코딩
			pw.println("join "+nickname);
			pw.flush();
			
			new ChatClientThread(socket).start();
			
			while(true) {
				// write
				System.out.print(">>");
				String line = scanner.nextLine();
				
				if("quit".equals(line)) {
					pw.println("quit");
					break;
				} else {
					// Base64 인코딩
					//byte[] lineBytes = line.getBytes();
					//byte[] encodeByte = Base64.getEncoder().encode(lineBytes);
					pw.println("msg "+line);
				}
			}		
			
		} catch (SocketException e) {
			log("Socket Exception: "+e);
		} catch (IOException e) {
			log("error: "+e);
		} finally {
			try {
				if(scanner !=null) {
					scanner.close();
				}
				if(socket!=null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void log(String message) {
		System.out.println("[Chat Client] "+message);
	}

}
