package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 서버 소켓 객체 생성
			serverSocket = new ServerSocket();
			
			// 2. bind
			// 소켓에 InetSocketAddress[InetAddress(IPAddress)+port]를 바인딩한다.(소켓에 주소 할당)
			// IPAddress: 0.0.0.0: 특정 호스트 IP를 바인딩하지 않는다.
			serverSocket.bind(new InetSocketAddress("0.0.0.0",60000));
			
			// 3. accept
			Socket socket = serverSocket.accept(); // blocking (주의할 점: blocking된 상태에서 다시 실행하지 말자. 꼭 끄고 다시 실행하기)
			
			try { // 4~ 과정 예외
				// 반대편 소켓 정보
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteSocketAddress.getPort();
				System.out.println("[server] connected by client ["+remoteHostAddress+" : "+remotePort+"]");
				
				// 4-1, 5-1. IO Stream 받아오기 (보조 스트림 필요한 경우 파이프 연결)
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				while(true) {
					// 4-2. read
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking
					if(readByteCount==-1) {
						// closed by client
						System.out.println("[server] closed by client");
						break;
					}
					// encoding
					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] received: "+data);
					
					// 5-2. write
					os.write(data.getBytes("utf-8")); // echo
				}

			} catch (SocketException e) {
				System.out.println("[server] Socket Exception: "+e);
			} catch (IOException e) {
				System.out.println("[server] error: "+e);
			} finally {
				try {
					if(socket!=null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) { // 1~3 과정 예외
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
