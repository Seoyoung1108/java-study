package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoRequestHandler extends Thread {
	private Socket socket;
	
	public EchoRequestHandler(Socket socket) {
		this.socket=socket;		
	}

	@Override
	public void run() {
		try {
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			EchoServer.log("connected by client ["+remoteHostAddress+" : "+remotePort+"]");
			
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
					EchoServer.log("closed by client");
					break;
				}
				EchoServer.log("received: "+data);
				
				// write
				pw.println(data); // echo
			}
		} catch (SocketException e) {
			EchoServer.log("Socket Exception: "+e);
		} catch (IOException e) {
			EchoServer.log("error: "+e);
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
	

}
