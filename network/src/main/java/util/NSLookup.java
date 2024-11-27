package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print("> ");
			String address = scanner.nextLine();
			
			if("exit".equals(address)) {
				break;
			}
			
			try {
				InetAddress[] InetAddresses = InetAddress.getAllByName(address); // 하나의 도메인에 IP 여러개 매핑 가능
				for(InetAddress inetAddress : InetAddresses) {
					System.out.println(address+": "+inetAddress.getHostAddress());
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}		
		scanner.close();	
	}

}
