package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	public static final int PORT = 50000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();
			
			serverSocket.bind(new InetSocketAddress("0.0.0.0",PORT));
			log("starts...[port:" + PORT + "]");
			
			Socket socket = serverSocket.accept();
			
			try {
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteSocketAddress.getPort();
				log("connected by client ["+remoteHostAddress+" : "+remotePort+"]");
				
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
						log("closed by client");
						break;
					}
					log("received: "+data);
					
					// write
					pw.println(data); // echo
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

		} catch (IOException e) {
			log("error: "+e);
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
	
	public static void log(String message) {
		System.out.println("[Echo Server] "+message);
	}

}
