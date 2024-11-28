package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class TCPClient {

	public static void main(String[] args) {
		Socket socket =null;
		
		try {
			// 1. 소켓 객체 생성
			socket=new Socket();
			
			// 2. connect (서버 연결)
			socket.connect(new InetSocketAddress("192.168.0.16",60000));
			
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
				System.out.println("[client] closed by server");
				return;
			}
			// encoding
			data = new String(buffer, 0, readByteCount, "utf-8");
			System.out.println("[client] received: "+data);
			
		} catch (SocketException e) {
			System.out.println("[client] Socket Exception: "+e);
		} catch (IOException e) {
			System.out.println("[client] error: "+e);
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
