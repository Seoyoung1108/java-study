package chat;

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

public class ChatClient {
	public static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;
		
		try {
			scanner = new Scanner(System.in);
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP,ChatServer.PORT));
			
			// writer, reader
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"), true); // autoFlush
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			
			// 먼저 join
			String nickname = "";
			while(nickname.length()==0) { // 빈 문자열 입력 시 재입력 요청
				nickname=inputJoin(scanner);
			}
			
			// Base64 인코딩
			byte[] nicknameBytes = nickname.getBytes(StandardCharsets.UTF_8);
			String encodedNickname = Base64.getEncoder().encodeToString(nicknameBytes);
			pw.println("join "+encodedNickname);
			pw.flush();
			String ack=br.readLine();
			if("1".equals(ack)) {
				System.out.println("입장하였습니다. 즐거운 채팅 되세요.");
			}
			
			new ChatClientThread(socket).start();
			
			while(true) {
				// write
                System.out.print(">>");
				String line = scanner.nextLine();
				
				if(line=="") { // 빈 문자열 입력 시 재입력 요청
					continue;
				}
				
				if("quit".equals(line)) {
					pw.println("quit");
					break;
				} else {
					// Base64 인코딩
					byte[] lineBytes = line.getBytes(StandardCharsets.UTF_8);
					String encodedLine = Base64.getEncoder().encodeToString(lineBytes);
					pw.println("msg "+encodedLine);
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
	
	public static String inputJoin(Scanner scanner) {
		System.out.print("닉네임>>"); 
		return scanner.nextLine().strip(); // 띄어쓰기 가능
	}
}
