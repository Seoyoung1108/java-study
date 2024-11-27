package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostName = inetAddress.getHostName(); // PC 이름
			String hostIpAddress = inetAddress.getHostAddress(); // IP
			
			System.out.println(hostName);
			System.out.println(hostIpAddress);
			
			byte[] IPAddresses = inetAddress.getAddress(); // IP 바이트로(진짜 IP 주소)
			for(byte IPAddress:IPAddresses) {
				System.out.println(IPAddress & 0x000000ff);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
