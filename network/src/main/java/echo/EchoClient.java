package echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient {
	public static final String SERVER_IP = "192.168.0.16";

	public static void main(String[] args) {
		Socket socket =null;
		
		try {
			// 1. 소켓 객체 생성
			socket=new Socket();
			
			// 2. connect (서버 연결)
			socket.connect(new InetSocketAddress(SERVER_IP,EchoServer.PORT));
			
			// 3-1, 4-1. IO Stream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			// 3-2. write
			String data = "Hello World";
			os.write(data.getBytes("utf-8"));
			
			// 4-2. read
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer);
			if(readByteCount==-1) {
				// closed by server
				log("closed by server");
				return;
			}
			// encoding
			data = new String(buffer, 0, readByteCount, "utf-8");
			log("received: "+data);
			
		} catch (IOException e) {
			log("error: "+e);
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
	
	public static void log(String message) {
		System.out.println("[Echo Client] "+message);
	}

}
