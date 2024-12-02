package chat.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

import chat.ChatServer;

public class ChatClientApp {
	public static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {		
		// 1. 서버 연결 작업
		Scanner scanner = null;
		Socket socket = null;
		String name = null;
		
		try {
			scanner = new Scanner(System.in);
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP,ChatServer.PORT));
			
			// 2. writer, reader
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"), true); // autoFlush
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			
			// 3. join
			while(true) {			
				System.out.println("대화명을 입력하세요.");
				System.out.print(">>> ");
				name = scanner.nextLine();
				
				if (name.isEmpty() == false) {
					break;
				}
				
				System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
			}
			
			// Base64 인코딩
			byte[] nicknameBytes = name.getBytes(StandardCharsets.UTF_8);
			String encodedNickname = Base64.getEncoder().encodeToString(nicknameBytes);
			pw.println("join "+encodedNickname);
			pw.flush();
			String ack=br.readLine();
			if("1".equals(ack)) {
				new ChatWindow(name, socket).show();
			}
			
			scanner.close();
		} catch (SocketException e) {
			log("Socket Exception: "+e);
		} catch (IOException e) {
			log("error: "+e);
		}
	}
	
	public static void log(String message) {
		System.out.println("[Chat Client App] "+message);
	}
}
