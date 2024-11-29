package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;


public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	List<PrintWriter> listWriters; // ChatServer 참조
	
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
			
			// IO Stream 받아오기 - 보조 스트림 사용 파이프 연결
			
			// writer
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"), true); // autoFlush
			
			// reader
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			
			while(true) {
				// read
				String data = br.readLine();
				if(data==null) {
					// closed by client
					ChatServer.log("closed by client");
					doQuit(pw);
					break;
				}
				
				// 프로토콜 분석
				ChatServer.log("received: "+data); // 나중에 지우기 - 확인용
				
				String[] tokens = data.split(":"); // 나중에 base64로 바꾸기 (프로토콜이랑 문자열 2개로 분리한다음 token[1]에는 문자열들 다 담아버리기)
				if("join".equals(tokens[0])) {
					doJoin(tokens[1],pw);
				} else if("msg".equals(tokens[0])) {
					doMessage(tokens[1],pw);
				} else if("quit".equals(tokens[0])) {
					doQuit(pw);
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


	private void doMessage(String string, PrintWriter pw) {
		broadcast(nickname+": "+string);
	}

	private void doJoin(String nickName, PrintWriter pw) {
		this.nickname=nickName;
		
		String data = nickName+"님이 참여하였습니다.";
		broadcast(data);
		
		addWriter(pw); // Writer Pool에 저장
		
		pw.println("입장하였습니다. 즐거운 채팅 되세요.");
		pw.flush();
	}

	private void addWriter(PrintWriter pw) {
		synchronized(listWriters) {
			this.listWriters.add(pw);
			// -> 식별을 어떻게??
		}
	}
	

	private void doQuit(PrintWriter pw) {
		removeWriter(pw); // Writer Pool에서 제거
		
		String data = nickname+"님이 퇴장하였습니다.";
		broadcast(data);
	}

	private void removeWriter(PrintWriter pw) {
		synchronized(listWriters) {
			this.listWriters.remove(pw);   // 그냥 이렇게 끝나면 되나?
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
