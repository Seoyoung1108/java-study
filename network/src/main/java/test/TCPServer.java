package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 소켓 객체 생성
			serverSocket = new ServerSocket();
			
			// 2. bind
			// 소켓에 InetSocketAddress[InetAddress(IPAddress)+port]를 바인딩 한다.
			// IPAddress: 0.0.0.0: 특정 호스트 IP를 바인딩하지 않는다.
			serverSocket.bind(new InetSocketAddress("0.0.0.0",5000));
			
			// 3. accept
			Socket socket = serverSocket.accept(); // blocking (주의할 점: blocking된 상태에서 다시 실행하지 말자. 꼭 끄고 다시 실행하기)
			System.out.println("연결 성공");
			
			// 4-1. IO Stream 받아오기
			InputStream is = socket.getInputStream();
			
			// 4-2. read
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer); // blocking
			if(readByteCount==-1) {
				// closed by client
				System.out.println("[server] closed by client");
				return;
			}
			// encoding
			String data = new String(buffer, 0, readByteCount, "utf-8");
			System.out.println("[server] receive: "+data);
		} catch (IOException e) {
			System.out.println("[server] error: "+e);
		} finally {
			try {
				if(serverSocket!=null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
