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
import java.util.List;

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	List<PrintWriter> listWriters;
	
	public ChatServerThread(Socket socket, List<PrintWriter> listWriters) {
		this.socket=socket;
		this.listWriters=listWriters;
	}
	
	@Override
	public void run() {
		try {
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			ChatServer.log("connected by client ["+remoteHostAddress+" : "+remotePort+"]");
					
			// writer, reader
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"), true); // autoFlush
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			
			while(true) {
				// read
				String data = br.readLine();
				if(data==null) {
					ChatServer.log("closed by client");
					doQuit(pw);
					break;
				}
				
				// 프로토콜 분석				
				String[] tokens = data.split(" ");
				if("join".equals(tokens[0])) {
					doJoin(tokens[1],pw);
				} else if("msg".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if("quit".equals(tokens[0])) {
					doQuit(pw);
					break;
				} else {
					ChatServer.log("error: 알 수 없는 요청("+tokens[0]+"]");
				}
			}
		} catch (SocketException e) {
			ChatServer.log("Socket Exception: "+e);
		} catch (IOException e) {
			ChatServer.log("error: "+e);
		} finally {
			try {
				if(socket!=null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doMessage(String line) {
		// Base64 디코딩
		byte[] decodedLine = Base64.getDecoder().decode(line);
		broadcast(nickname+": "+new String(decodedLine,StandardCharsets.UTF_8));
	}

	private void doJoin(String nickName, PrintWriter pw) {
		// Base64 디코딩
		byte[] decodedNickname = Base64.getDecoder().decode(nickName);
		this.nickname=new String(decodedNickname,StandardCharsets.UTF_8);
		
		String data = nickname+"님이 입장하였습니다.";
		broadcast(data);
		
		addWriter(pw); // Writer Pool에 저장
		
		pw.println("입장하였습니다. 즐거운 채팅 되세요.");
		pw.flush();
	}

	private void addWriter(PrintWriter pw) {
		synchronized(listWriters) {
			listWriters.add(pw);
		}
	}

	private void doQuit(PrintWriter pw) {
		removeWriter(pw); // Writer Pool에서 제거
		
		String data = nickname+"님이 퇴장하였습니다.";
		broadcast(data);
	}

	private void removeWriter(PrintWriter pw) {
		synchronized(listWriters) {
			listWriters.remove(pw);
		}	
	}
	
	// 모든 클라이언트에게 메시지를 보내는 메소드(입장은 추가 전, 퇴장은 제거 후)
	private void broadcast(String data) {
		synchronized(listWriters){
			for(PrintWriter printwriter:listWriters) {
				printwriter.println(data);
				printwriter.flush();
			}
		}
	}
}
